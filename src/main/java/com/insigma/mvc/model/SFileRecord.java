package com.insigma.mvc.model;

import java.util.Date;


/**
 *  文件记录表
 * 
 */
public class SFileRecord extends PageInfo implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String file_uuid;
	private String file_name;
	private String file_length;
	private String file_status;
	private String file_path;
	private Date file_addtime;
	private String file_md5;
	private String file_type;
	private String file_bus_id;
	private String file_bus_type;
	private String selectnodes;
	private String upload_callback;
	
	
	
	public String getUpload_callback() {
		return upload_callback;
	}
	public void setUpload_callback(String upload_callback) {
		this.upload_callback = upload_callback;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getFile_bus_id() {
		return file_bus_id;
	}
	public void setFile_bus_id(String file_bus_id) {
		this.file_bus_id = file_bus_id;
	}
	public String getFile_bus_type() {
		return file_bus_type;
	}
	public void setFile_bus_type(String file_bus_type) {
		this.file_bus_type = file_bus_type;
	}
	public String getFile_length() {
		return file_length;
	}
	public void setFile_length(String file_length) {
		this.file_length = file_length;
	}
	public String getFile_status() {
		return file_status;
	}
	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}
	public String getFile_uuid() {
		return file_uuid;
	}
	public void setFile_uuid(String file_uuid) {
		this.file_uuid = file_uuid;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public Date getFile_addtime() {
		return file_addtime;
	}
	public void setFile_addtime(Date file_addtime) {
		this.file_addtime = file_addtime;
	}
	public String getFile_md5() {
		return file_md5;
	}
	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	public String getSelectnodes() {
		return selectnodes;
	}
	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}
	
	

}
