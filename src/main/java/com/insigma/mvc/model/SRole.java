package com.insigma.mvc.model;

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
	
    private String roleid;
	@NotEmpty(message="角色名称不能为空")
	private String name;
	@NotEmpty(message="角色编码不能为空")
    private String code;
	@NotEmpty(message="角色描述不能为空")
    private String describe;

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
