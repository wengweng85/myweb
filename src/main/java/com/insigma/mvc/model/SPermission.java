package com.insigma.mvc.model;

import java.util.Date;

import javax.validation.constraints.NotNull;


/**
 *  权限表
 * 
 */
public class SPermission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**权限编码*/
	@NotNull(message="权限编码不能为空")
	private String code;
	/**权限名称*/
	@NotNull(message="权限名称不能为空")
	private String name;
	/**权限描述*/
	@NotNull(message="权限描述不能为空")
	private String describe;
	/**权限地址*/
	private String url;
	/**权限类型*/
	@NotNull(message="权限类型不能为空")
	private String type;
	/**权限编号*/
	private String permissionid;
	/**权限编号*/
	private String id;
	/**权限父结点编号*/
	private String parentid;
	/**权限父结点名称*/
	private String parentname;
	/**权限父结点编号*/
	private String pid;
	/**是否打开*/
	private String open;
	/**排序号*/
	private String sortnum;
	
	private Date updatetime;
	
	
	
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}
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
