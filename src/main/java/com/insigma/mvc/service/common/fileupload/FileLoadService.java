package com.insigma.mvc.service.common.fileupload;

import java.io.InputStream;
import java.util.HashMap;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SFileRecord;




/**
 * 文件上传service
 * @author wengsh
 *
 */
public interface FileLoadService {
	 public SFileRecord getFileInfo(String file_uuid);  
     public byte[]  download(String file_path);  
     public String upload(String originalFilename,String file_bus_id,String file_bus_type,InputStream in);  
 	 public HashMap<String,Object> getFileList( SFileRecord sFileRecord );
 	 public AjaxReturnMsg<String> deleteFileByFileUuid(String file_uuid);
 	public AjaxReturnMsg<String> batDeleteData(SFileRecord sFileRecord) ;
 	 
}
