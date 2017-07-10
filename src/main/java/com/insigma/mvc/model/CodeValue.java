package com.insigma.mvc.model;


public class CodeValue implements java.io.Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String code_type;
	private String code_value;
	private String code_name;
	private String par_code_value;
	private String code_describe;
	private String code_spelling;
	private String code_level;
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
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
	public String getCode_spelling() {
		return code_spelling;
	}
	public void setCode_spelling(String code_spelling) {
		this.code_spelling = code_spelling;
	}
	public String getCode_level() {
		return code_level;
	}
	public void setCode_level(String code_level) {
		this.code_level = code_level;
	}
	
	
	

}