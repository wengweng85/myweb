package com.insigma.mvc.model;


/**
 *  ”√ªß±Ì
 * 
 */
public class SPermission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String url;
	private String type;
	private String iconcss;
	private String permissionid;
	private String parentid;
	
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
	public String getIconcss() {
		return iconcss;
	}
	public void setIconcss(String iconcss) {
		this.iconcss = iconcss;
	}

	
}
