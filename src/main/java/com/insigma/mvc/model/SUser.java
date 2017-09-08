package com.insigma.mvc.model;



/**
 *  ”√ªß±Ì
 * 
 */
public class SUser implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid;
	private String cnname;
	private String password;
	private String username;
	private String enabled;
	private String groupid;
	private String groupname;
	private String groupparentid;
	private String type;
	private String isgrant;
	private String isvercode;
	private String verifycode;
	
	public String getIsvercode() {
		return isvercode;
	}
	public void setIsvercode(String isvercode) {
		this.isvercode = isvercode;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupparentid() {
		return groupparentid;
	}
	public void setGroupparentid(String groupparentid) {
		this.groupparentid = groupparentid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsgrant() {
		return isgrant;
	}
	public void setIsgrant(String isgrant) {
		this.isgrant = isgrant;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
