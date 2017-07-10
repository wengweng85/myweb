package com.insigma.rpc.infostruct.enums;

/**
 * 接口名称
 * @author wengsh
 *
 */
public enum BusCode {
	JK_111091("111091","查询代办事项数目"),
	JK_211001("211001","查询变更过的机构信息"),
	JK_211002("211002","机构信息映射绑定"),
	JK_211021("211021","查询变更过的用户信息"),
	JK_211022("211022","用户信息映射绑定"),
	
	JK_212001("212001","新增人员个人信息"),
	JK_212002("212002","修改个人信息"),
	JK_212003("212003","查询个人信息"),
	JK_212004("212004","查询修改过的个人信息"),
	
	//社保接口
	JK_22KOW6("22KOW6","个人参保信息查询"),
	JK_Q1BQ7U("Q1BQ7U","社会保险缴费信息查询");
	
	private String code;
	private String name;
	
	private BusCode(String code,String name){
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
