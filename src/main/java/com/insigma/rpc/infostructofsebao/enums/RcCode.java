package com.insigma.rpc.infostructofsebao.enums;

/**
 * 交易返回码
 * @author wengsh
 *
 */
public enum RcCode {
	
	SUCCESS("0","成功"),
	FAIL("1","业务异常"),
	ERROR("2","服务器异常"),
	;
	
	//交易返回码
	private String code;
	//交易返回码说明 
	private String name;
	
	private RcCode(String code,String name){
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
