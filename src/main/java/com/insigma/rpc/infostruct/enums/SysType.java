package com.insigma.rpc.infostruct.enums;

/**
 * 系统类型
 * @author wengsh
 *
 */
public enum SysType {
	MHXT("MHXT","基础信息库"),
	JYGL("JYGL","就业管理"),
	SYTH("SYTH","社会保险一体化"),
	RYKH("RYKH","人员考核"),
	XZGL("XZGL","行政管理"),
	ZYJS("ZYJS","职业介绍"),
	BMOA("BMOA","部门OA"),
	ZXFW("ZXFW","咨询服务"),
	RLSB("RLSB","杭州人力社保平台"),
	LDJC("LDJC","劳动监察"),
	DBBS("DBBS","党建BBS"),
	BGOA("BGOA","办公OA"),
	PXJD("PXJD","培训鉴定"),
	;
	
	private String code;
	private String name;
	
	private SysType(String code,String name){
		this.code=code;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	

}
