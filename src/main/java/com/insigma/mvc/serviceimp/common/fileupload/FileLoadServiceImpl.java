package com.insigma.mvc.serviceimp.common.fileupload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.common.util.RandomNumUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.common.fileupload.FileLoadMapper;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.service.common.fileupload.FileLoadService;
import com.insigma.mvc.service.sysmanager.codetype.SysCodeTypeService;

/**
 *
 * @author wengsh
 *
 */
@Service
public class FileLoadServiceImpl  extends MvcHelper<SFileRecord> implements FileLoadService {

	@Value("${localdir}")
	private String localdir;

	@Resource
	private FileLoadMapper fileLoadMapper;
	
	@Resource
	private SysCodeTypeService sysCodeTypeService;

	public String getLocaldir() {
		return localdir;
	}

	public void setLocaldir(String localdir) {
		this.localdir = localdir;
	}
	
	
	/**
	 * 通过业务id获取文件列表
	 */
	@Override
	public HashMap<String, Object> getFileList(SFileRecord sFileRecord) {
	    //PageHelper.offsetPage(sFileRecord.getOffset(), sFileRecord.getLimit());
		List<SFileRecord> list=fileLoadMapper.getBusFileRecordListByBusId(sFileRecord);
		PageInfo<SFileRecord> pageinfo = new PageInfo<SFileRecord>(list);
		return this.success_hashmap_response(pageinfo);
	}
	
	
	

	/**
	 * 下载
	 */
	@Override
	public byte[] download(String file_path) {
		InputStream data = null;
		try {
			data = new FileInputStream(file_path);
			int size = data.available();
			byte[] buffer = new byte[size];
			IOUtils.read(data, buffer);
			return buffer;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(data);
		}
	}

	/**
	 * 上传
	 */
	@Override
	@Transactional
	public String upload(String originalFilename,String file_bus_id,String file_bus_type,InputStream in){
		try {
            /**拷贝输入流*/
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int len;
			while ((len = in.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			in.close();
			/**复制成两个输入流，一个用于计算md5,一个用于生成文件*/
			InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
			InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
			String file_md5 = DigestUtils.md5Hex(is1);
			is1.close();

			SFileRecord sfilerecord = fileLoadMapper.getFileUploadRecordByFileMd5(file_md5);
			/** 如果通过md5判断文件，已经在服务器上存在此文件，不重复保存 **/
			if (sfilerecord == null) {
				sfilerecord = new SFileRecord();
				sfilerecord.setFile_name(originalFilename);// 文件名
				
				/** 构建图片保存的目录 **/
				/** 当前月份 **/
				String ym = new SimpleDateFormat("yyyyMM").format(new Date());
				/** 根据真实路径创建目录 **/
				File fileuploadDir = new File(localdir + "/" + ym);
				if (!fileuploadDir.exists()) {
					fileuploadDir.mkdirs();
				}
				/** 本地保存的最终文件完整路径 **/
				String filepath = localdir + "/" + ym + "/" + originalFilename;
				File file = new File(filepath);
				//如果同名的文件存在
				if(file.exists()){
					int indexofdoute = originalFilename.lastIndexOf(".");
					/**文件名及后缀*/
					String prefix = originalFilename.substring(0, indexofdoute);
					String endfix = originalFilename.substring(indexofdoute).toLowerCase();
					sfilerecord.setFile_type(endfix);
					/**新文件名按日期+随机生成*/
					prefix += new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ '_' + RandomNumUtil.getRandomString(6);
					filepath=localdir + "/" + ym + "/"+prefix+endfix;
					file=new File(filepath);
				}
				sfilerecord.setFile_path(filepath);
				sfilerecord.setFile_status("0");//无效
				OutputStream os = new FileOutputStream(file);
				int bytesRead = 0;
				buffer = new byte[8192];
				while ((bytesRead = is2.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.flush();
				os.close();
				is2.close();
				baos.close();
				
				sfilerecord.setFile_length(new Long(file.length()).toString());
				sfilerecord.setFile_type(sfilerecord.getFile_name().substring(sfilerecord.getFile_name().lastIndexOf(".")+1));
				sfilerecord.setFile_md5(file_md5);// 文件MD5计算器
				//保存文件记录
				fileLoadMapper.saveFileRecord(sfilerecord);
				//sysCodeTypeService.setSelectCacheData("FILENAME");
			}
			
			sfilerecord.setFile_bus_id(file_bus_id);
			sfilerecord.setFile_bus_type(file_bus_type);
			sfilerecord.setBus_status("0");
			//保存业务记录
			fileLoadMapper.saveBusRecord(sfilerecord);
			return JSONObject.fromObject(sfilerecord).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 得到文件信息
	 */
	@Override
	public SFileRecord getFileInfo(String file_uuid) {
		return fileLoadMapper.getBusFileRecordByBusId(file_uuid);
	}

	
	@Override
	public AjaxReturnMsg<String> deleteFileByBusUuid(String bus_uuid) {
		//记录删除
		int deletenum= fileLoadMapper.deleteFileByBusUuid(bus_uuid);
		if(deletenum==1){
			return this.success("删除成功");
		}else{
			return this.error("删除失败,请确定文件是否存在");
		}
	}

	/**
	 * 批量删除
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> batDeleteData(SFileRecord sFileRecord) {
		String [] ids=sFileRecord.getSelectnodes().split(",");
		for(int i=0;i<ids.length;i++){
			deleteFileByBusUuid(ids[i]);
		}
		sysCodeTypeService.setSelectCacheData("FILENAME");
		return this.success("批量删除成功");
		/*int batdeletenum=fileLoadMapper.batDeleteData(ids);
		if(batdeletenum==ids.length){
			return this.success("批量删除成功");
		}else{
			return this.success("批量删除成功,但存在失败的数据,请检查");
		}*/
	}

	
	@Override
	@Transactional
	public AjaxReturnMsg<String> batupdateBusIdByBusUuidArray( Map<String,Object> map) {
		  fileLoadMapper.batupdateBusIdByBusUuidArray(map);
		  return this.success("更新成功");
	}

}