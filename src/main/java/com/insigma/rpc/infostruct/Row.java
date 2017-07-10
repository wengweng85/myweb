package com.insigma.rpc.infostruct;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author Administrator
 *
 */
@XmlRootElement
public class Row implements java.io.Serializable {
	/**
	 * 
	 * 
	 * 
	机构基本信息项
	NDNAME	节点机构名称	string	100	
	NDABBR	节点机构简称	string	50	
	NDLEAD	节点机构负责人	string	50	
	NDADDR	节点机构所在地址	string	100	
	CONTACT	联系人	string	50	
	PHONE	联系电话	string	20	
	PRIORITY	排序	number	8	
	GRADE	机构等级	string	10	
	DESCRIBE	机构职能描述	string	500	
 
	个人基本信息项目
	
	
	LGNAME	登录名	string	20	
	NAME	姓名	string	50	
	GENDER	性别	string	1	
	IDNO	身份证号码	string	20	
	MPHONE	移动电话	string	20	
	VPHONE	移动电话（虚拟号）	string	20	
	OPHONE	办公电话	string	20	
	DUTIES	职务	string	500	
	LEVEL	职级	string	500	
	POST	岗位	string	500	
	EMAIL	电子邮箱（内网）	string	50	
	INEMAIL	电子邮箱（公网）	string	50	
	PRIORITY	用户所属机构及排名	string	1000	数据格式为：
	目标机构ID=排名[;目标机构2ID=排名]
	用户可以属于多个机构，以;分割。
	10001=1：表示在目标机构10001中排名第1位
	10001=0或者10001=：表示在目标机构10001中无排名顺序
	10001=负数：表示从目标机构10001中移除
	 */
	private String CODE;//指标项代码
	private String VALUE;//修改后的值
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String code) {
		CODE = code;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String value) {
		VALUE = value;
	}
	
	
	
	
}
