package com.insigma.rpc.infostruct.enums;

/**
 * 业务返回码
 * @author wengsh
 *
 */
public enum BusiCode {
	SUCCESS("0000","成功"),
	;
	//业务返回码
	private String value;
	//业务返回码说明 
	private String name;
	
	private BusiCode(String value,String name){
		this.value=value;
		this.name=name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
