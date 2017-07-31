package com.insigma.mvc.model;

import org.codehaus.jackson.annotate.JsonIgnore;


public class CodeValue implements java.io.Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String code_type;
	@JsonIgnore
	private String q;
	private String id;
	private String text;
	@JsonIgnore
	private String code_value;
	@JsonIgnore
	private String code_name;
	@JsonIgnore
	private String aaa102;
	@JsonIgnore
	private String aaa103;
	@JsonIgnore
	private String par_code_value;
	@JsonIgnore
	private String code_describe;
	@JsonIgnore
	private String code_spelling;
	@JsonIgnore
	private String code_level;
	
	
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getAaa102() {
		return aaa102;
	}

	public void setAaa102(String aaa102) {
		this.aaa102 = aaa102;
	}

	public String getAaa103() {
		return aaa103;
	}

	public void setAaa103(String aaa103) {
		this.aaa103 = aaa103;
	}

	public String getCode_type() {
		return code_type;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
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