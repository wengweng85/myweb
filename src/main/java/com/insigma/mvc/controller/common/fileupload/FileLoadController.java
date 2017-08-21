package com.insigma.mvc.controller.common.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.controller.common.suggest.SuggestSearchController;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SFileRecord;
import com.insigma.mvc.service.common.fileupload.FileLoadService;
import com.insigma.mvc.service.sysmanager.codetype.SysCodeTypeService;
import com.insigma.resolver.AppException;

/**
 * 文件上传及下载
 */
@Controller
@RequestMapping(value = "/common/fileload")
public class FileLoadController extends MvcHelper<SFileRecord> {


    Log log = LogFactory.getLog(SuggestSearchController.class);

    @Resource
    private FileLoadService fileloadservice;
    
    @Resource
    private SysCodeTypeService syscodetypeservice;
    
    
    /**
   	 * 跳转至上传页面
   	 * @param request
   	 * @return
   	 */
   	@RequestMapping("/tofilelist")
   	public ModelAndView tofilelist(HttpServletRequest request,Model model,SFileRecord sFileRecord ) throws Exception {
   		ModelAndView modelAndView=new ModelAndView("common/fileupload/filelist");
   		if(sFileRecord.getFile_bus_id()==null){
   			throw new Exception("业务编号不能为空");
   		}
   		if(sFileRecord.getFile_bus_type()==null){
   			throw new Exception("业务图片类型不能为空");
   		}
   		CodeValue codevalue=new CodeValue();
   		codevalue.setCode_type("FILE_BUS_TYPE");
   		codevalue.setCode_value(sFileRecord.getFile_bus_type());
   		if(syscodetypeservice.getCodeValueByValue(codevalue)==null){
   			throw new Exception("业务图片类型编码"+sFileRecord.getFile_bus_type()+"不存在,请检查代码类型FILE_BUS_TYPE中是否存在!");
   		}
   		
   		modelAndView.addObject("filerecord", sFileRecord);
   		return modelAndView;
   	}
   	
	/**
	 * 通过人员id获取文件列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getFileList")
	@ResponseBody
	public HashMap<String,Object> getUserListByGroupid(HttpServletRequest request,Model model,SFileRecord sFileRecord ) throws Exception {
		return fileloadservice.getFileList(sFileRecord);
	}
	
    
    /**
	 * 跳转至图片上传页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toImgUpload")
	public ModelAndView toupload(HttpServletRequest request,Model modell,SFileRecord sFileRecord) throws Exception {
		ModelAndView modelAndView=new ModelAndView("common/fileupload/imgUpload");
		modelAndView.addObject("filerecord", sFileRecord);
		return modelAndView;
	}
	
	   /**
		 * 跳转至文件上传页面
		 * @param request
		 * @return
		 */
		@RequestMapping("/toFileUpload")
		public ModelAndView toFileUpload(HttpServletRequest request,Model modell,SFileRecord sFileRecord) throws Exception {
			ModelAndView modelAndView=new ModelAndView("common/fileupload/fileUpload");
			modelAndView.addObject("filerecord", sFileRecord);
			return modelAndView;
		}
	
	
	/**
     * 文件上传
     *
     * @param request
     * @param response
     * @return
     * @throws AppException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String file_bus_id=request.getParameter("file_bus_id");
		String file_bus_type=request.getParameter("file_bus_type");
        //检查业务编号参数
        /*if(null==file_bus_id||file_bus_id.equals("")){
        	this.error( "业务编号参数为空,请检查");
        	return ;
        }
        
      //检查业务编号参数
        if(null==file_bus_type||file_bus_type.equals("")){
        	this.error( "文件业务类型参数为空,请检查");
        	return ;
        }
        
        CodeValue codevalue=new CodeValue();
   		codevalue.setCode_type("FILE_BUS_TYPE");
   		codevalue.setCode_value(file_bus_type);
   		if(syscodetypeservice.getCodeValueByValue(codevalue)==null){
   			this.error("业务图片类型编码"+file_bus_type+"不存在,请检查代码类型FILE_BUS_TYPE中是否存在!");
   			return ;
   		}*/
        
        
		long MAX_SIZE = 20* 1024 * 1024L;//100m
		
    	try {
            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    //取得上传文件
                    MultipartFile multipartFile = multiRequest.getFile(iter.next());
                    if (multipartFile.getSize() > MAX_SIZE) {
                        this.error( "文件尺寸超过规定大小:" + MAX_SIZE / 1024 / 1024 + "M");
                        break;
                    } else {
                       
                        // 得到去除路径的文件名
                        String originalFilename = multipartFile.getOriginalFilename();
                        int indexofdoute = originalFilename.lastIndexOf(".");
                        
                        /**获取文件的后缀**/
                        String endfix = "";
                        if (indexofdoute != -1) {
                            // 尾
                            endfix = originalFilename.substring(indexofdoute).toLowerCase();
                            if(endfix.equals(".jpg")||endfix.equals(".jpeg")||endfix.equals(".gif")||endfix.equals(".png") ||endfix.equals(".pdf")||endfix.equals(".doc")||endfix.equals(".docx")||endfix.equals(".xls")||endfix.equals(".xlsx")||endfix.equals(".rar")||endfix.equals(".zip")) {
	     						//上传并记录日志
                            	String id=fileloadservice.upload(originalFilename,file_bus_id,file_bus_type,multipartFile.getInputStream() );
                            	System.out.println(id);
                                this.success( id);
                            }else{
                                this.error("文件格式不正确,请确认,只允许上传格式为jpg、jpeg、gif、png、pdf、doc、docx、xls、xlsx、rar、zip格式的文件!!");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
			// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				this.error( "文件尺寸超过规定大小:" + MAX_SIZE / 1024 / 1024 + "M");
			}
			this.error(e.getMessage());
        }

    }

    
    /**
	 * 通过id删除文件信息
	 * @param request
	 * @param model
	 * @param aac001
	 * @return
	 */
	@RequestMapping("/deletebyid/{id}")
	@ResponseBody
	public AjaxReturnMsg deleteFileByid(HttpServletRequest request,Model model,@PathVariable String id){
		return fileloadservice.deleteFileByFileUuid(id);
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param model
	 * @param aac001
	 * @return
	 */
	@RequestMapping("/batdelete")
	@ResponseBody
	public AjaxReturnMsg<String> batdelete(HttpServletRequest request,Model model,SFileRecord sFileRecord){
		return fileloadservice.batDeleteData(sFileRecord);
	}
	
    /**
     * 文件下载
     * @param request
     * @param response
     * @throws AppException
     */
    @RequestMapping(value = "/download/{file_uuid}")
    public void download(@PathVariable(value="file_uuid") String file_uuid, HttpServletRequest request ,HttpServletResponse response) throws  AppException{
        try{
        	SFileRecord filerecord=fileloadservice.getFileInfo(file_uuid);
        	if(filerecord!=null){
        		 byte[] temp=fileloadservice.download(filerecord.getFile_path());
                 if(temp!=null){
                 	//此行代码是防止中文乱码的关键！！
                     response.setHeader("Content-disposition","attachment; filename="+ new String(filerecord.getFile_name().getBytes("GBK"),"iso-8859-1"));
                     BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(temp));
                     BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                     //新建一个2048字节的缓冲区
                     byte[] buff = new byte[2048];
                     int bytesRead=0;
                     while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                         bos.write(buff,0,bytesRead);
                     }
                     bos.flush();
                     if (bis != null)
                         bis.close();
                     if (bos != null)
                         bos.close();
                 }else{
                 	throw new Exception("下载错误,不存在的id");
                 }
        	}
        }catch(Exception e){
            //log.error(e.getMessage());
        }
    }

}
