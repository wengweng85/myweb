package com.insigma.mvc.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class CodeType implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="代码类型不能为空")
    private String code_type;
    
	@JsonIgnore
    private String aaa100;
    
    private String id;
    
    private String name;
    
    @NotNull(message="代码名称不能为空")
    private String type_name;
    
    private String code_root_value;
    
    private String code_seq;
    
    private String isParent;
    
    @JsonIgnore
    private String isupdate;//是否更新标志
    
    @JsonIgnore
    private String q_code_type;
    @JsonIgnore
    private String q_type_name;
    
	@JsonIgnore
	private String q_code_value;
	@JsonIgnore
	private String q_code_name;
    
    
	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getAaa100() {
		return aaa100;
	}

	public void setAaa100(String aaa100) {
		this.aaa100 = aaa100;
	}

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

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getCode_root_value() {
		return code_root_value;
	}

	public void setCode_root_value(String code_root_value) {
		this.code_root_value = code_root_value;
	}

	public String getCode_seq() {
		return code_seq;
	}

	public void setCode_seq(String code_seq) {
		this.code_seq = code_seq;
	}

	public String getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(String isupdate) {
		this.isupdate = isupdate;
	}

	public String getQ_code_type() {
		return q_code_type;
	}

	public void setQ_code_type(String q_code_type) {
		this.q_code_type = q_code_type;
	}

	public String getQ_type_name() {
		return q_type_name;
	}

	public void setQ_type_name(String q_type_name) {
		this.q_type_name = q_type_name;
	}

	public String getQ_code_value() {
		return q_code_value;
	}

	public void setQ_code_value(String q_code_value) {
		this.q_code_value = q_code_value;
	}

	public String getQ_code_name() {
		return q_code_name;
	}

	public void setQ_code_name(String q_code_name) {
		this.q_code_name = q_code_name;
	}
	
	
	
}