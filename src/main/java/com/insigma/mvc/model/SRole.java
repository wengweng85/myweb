package com.insigma.mvc.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *  用户表
 * 
 */
public class SRole extends PageInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String userid;
   
	private String roleid;
	
	@NotNull(message="角色名称不能为空")
	private String name;
	
	@NotNull(message="角色编码不能为空")
    private String code;
	
	@NotNull(message="角色描述不能为空")
    private String describe;
	
	private String pid;
	private String open;
	private String checked;
	@JsonIgnore
	private String permissionid;
	
	@JsonIgnore
	private String selectnodes;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSelectnodes() {
		return selectnodes;
	}

	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}
	
	 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
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

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
