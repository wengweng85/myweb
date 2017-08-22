package com.insigma.mvc.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;


public class CodeValue implements java.io.Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String code_seq;
	@NotNull(message="代码值不能为空")
	private String code_value;
	@NotNull(message="代码名称不能为空")
	private String code_name;
	private String id;
	private String name;
	private String isParent;
	private String code_describe;
	@JsonIgnore
	private String code_type;
	private String par_code_value;
	private String par_code_name;
	
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getPar_code_value() {
		return par_code_value;
	}
	public void setPar_code_value(String par_code_value) {
		this.par_code_value = par_code_value;
	}
	public String getCode_describe() {
		return code_describe;
	}
	public void setCode_describe(String code_describe) {
		this.code_describe = code_describe;
	}
	public String getPar_code_name() {
		return par_code_name;
	}
	public void setPar_code_name(String par_code_name) {
		this.par_code_name = par_code_name;
	}
	public String getCode_seq() {
		return code_seq;
	}
	public void setCode_seq(String code_seq) {
		this.code_seq = code_seq;
	}
}