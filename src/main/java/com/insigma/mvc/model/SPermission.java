package com.insigma.mvc.model;


/**
 *  Ȩ�ޱ�
 * 
 */
public class SPermission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Ȩ�ޱ���*/
	private String code;
	/**Ȩ������*/
	private String name;
	/**Ȩ������*/
	private String describe;
	/**Ȩ�޵�ַ*/
	private String url;
	/**Ȩ������*/
	private String type;
	/**Ȩ�ޱ��*/
	private String permissionid;
	/**Ȩ�ޱ��*/
	private String id;
	/**Ȩ�޸������*/
	private String parentid;
	/**Ȩ�޸��������*/
	private String parentname;
	/**Ȩ�޸������*/
	private String pid;
	/**�Ƿ��*/
	private String open;
	
	
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
