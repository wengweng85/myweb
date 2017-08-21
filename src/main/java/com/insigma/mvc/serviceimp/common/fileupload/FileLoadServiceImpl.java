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

import javax.annotation.Resource;

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
public class FileLoadServiceImpl  extends MvcHelper implements FileLoadService {

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
	 * ͨ��ҵ��id��ȡ�ļ��б�
	 */
	@Override
	public HashMap<String, Object> getFileList(SFileRecord sFileRecord) {
		PageHelper.offsetPage(sFileRecord.getOffset(), sFileRecord.getLimit());
		List<SFileRecord> list=fileLoadMapper.getFileListByBusId(sFileRecord);
		PageInfo<SFileRecord> pageinfo = new PageInfo<SFileRecord>(list);
		return this.success_hashmap_response(pageinfo);
	}

	/**
	 * ����
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
	 * �ϴ�
	 */
	@Override
	public String upload(String originalFilename,String file_bus_id,String file_bus_type,InputStream in){
		try {
            /**����������*/
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int len;
			while ((len = in.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			in.close();
			/**���Ƴ�������������һ�����ڼ���md5,һ�����������ļ�*/
			InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
			InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
			String file_md5 = DigestUtils.md5Hex(is1);
			is1.close();

			SFileRecord md5filerecord = fileLoadMapper.getFileUploadRecordByFileMd5(file_md5);
			/** ���ͨ��md5�ж��ļ����Ѿ��ڷ������ϴ��ڴ��ļ������ظ����� **/
			if (md5filerecord != null) {
				return md5filerecord.getFile_uuid();
			} else {
				SFileRecord sfilerecord = new SFileRecord();
				sfilerecord.setFile_name(originalFilename);// �ļ���
				
				/** ����ͼƬ�����Ŀ¼ **/
				/** ��ǰ�·� **/
				String ym = new SimpleDateFormat("yyyyMM").format(new Date());
				/** ������ʵ·������Ŀ¼ **/
				File fileuploadDir = new File(localdir + "/" + ym);
				if (!fileuploadDir.exists()) {
					fileuploadDir.mkdirs();
				}
				/** ���ر���������ļ�����·�� **/
				String filepath = localdir + "/" + ym + "/" + originalFilename;
				File file = new File(filepath);
				//���ͬ�����ļ�����
				if(file.exists()){
					int indexofdoute = originalFilename.lastIndexOf(".");
					/**�ļ�������׺*/
					String prefix = originalFilename.substring(0, indexofdoute);
					String endfix = originalFilename.substring(indexofdoute).toLowerCase();
					sfilerecord.setFile_type(endfix);
					/**���ļ���������+�������*/
					prefix += new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ '_' + RandomNumUtil.getRandomString(6);
					filepath=localdir + "/" + ym + "/"+prefix+endfix;
					file=new File(filepath);
				}
				sfilerecord.setFile_bus_id(file_bus_id);
				sfilerecord.setFile_bus_type(file_bus_type);
				sfilerecord.setFile_path(filepath);
				sfilerecord.setFile_status("0");//��Ч
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
				sfilerecord.setFile_md5(file_md5);// �ļ�MD5������
				fileLoadMapper.saveFileUploadRecord(sfilerecord);
				sysCodeTypeService.setSelectCacheData("FILENAME");
				return sfilerecord.getFile_uuid();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * �õ��ļ���Ϣ
	 */
	@Override
	public SFileRecord getFileInfo(String file_uuid) {
		return fileLoadMapper.getFileUploadRecordByFileUUid(file_uuid);
	}

	
	@Override
	public AjaxReturnMsg<String> deleteFileByFileUuid(String file_uuid) {
		SFileRecord sfilerecored=fileLoadMapper.getFileUploadRecordByFileUUid(file_uuid);
		//�ж��ļ��Ƿ����
		if(sfilerecored!=null){
			//����ɾ��
			File file=new File(sfilerecored.getFile_path());
			file.delete();
		}
		//��¼ɾ��
		int deletenum= fileLoadMapper.deleteFileByFileUuid(file_uuid);
		if(deletenum==1){
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��,��ȷ���ļ��Ƿ����");
		}
	}

	/**
	 * ����ɾ��
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> batDeleteData(SFileRecord sFileRecord) {
		String [] ids=sFileRecord.getSelectnodes().split(",");
		for(int i=0;i<ids.length;i++){
			deleteFileByFileUuid(ids[i]);
		}
		sysCodeTypeService.setSelectCacheData("FILENAME");
		return this.success("����ɾ���ɹ�");
		/*int batdeletenum=fileLoadMapper.batDeleteData(ids);
		if(batdeletenum==ids.length){
			return this.success("����ɾ���ɹ�");
		}else{
			return this.success("����ɾ���ɹ�,������ʧ�ܵ�����,����");
		}*/
	}

}