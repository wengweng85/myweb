package com.insigma.rpc.infostruct.enums;

/**
 * 交易返回码
 * @author wengsh
 *
 */
public enum TradeCode {
	
	SUCCESS("0000","交易成功"),
	;
	
	//交易返回码
	private String code;
	//交易返回码说明 
	private String name;
	
	private TradeCode(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
