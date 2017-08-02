package com.insigma.rpc.infostruct;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 报文结构
 * @author wengsh
 */
@XmlRootElement(name="PACKET")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message implements java.io.Serializable {
	
	private String VERSION="2016";//版本号
	private String SNDCODE;//交易发起方
	
	//@XmlJavaTypeAdapter(CDataAdapter.class)
	private String RCVCODE;//交易接收方
	private String MSGCODE;//交易消息码
	private String SNDMSGID;//发起方报文ID
	private String USERCODE;//操作员编号
	private String USERNAME;//操作员姓名
	private String SNDTIME;//交易请求时间
	private String RCVTIME;//交易响应时间
	private Integer DATASIZE;//业务体条数
	
	@XmlElementWrapper(name="DATALIST")
	@XmlElement(name="DATA")
	
	private List<Data> DATALIST;//业务体
	private Integer PAGENO;//页码
	private String EOPSIGN;//尾页标志
	private String RCVMSGID;//接收方报文ID
	
	/**
	 *  0000	成功	业务处理成功
		0001	连接失败	网络错误
		0002	通讯超时	网络超时
		0003	传输数据失败	传输数据时出错
		0004	交易消息码对应的服务未找到	交易消息码对应的Service未实现或配置错误
		0050	暂未开放目标系统接入服务	
		0051	个人或单位ID映射配置不存在	
		0052	个人或单位指标项信息不存在	
		0100	报文解析失败	
		0101	报文数据为空	
		0102	交易发起方为空	
		0103	交易接收方为空	
		0104	报文业务体为空	
		0105	报文业务体有多条	
		0500	入参不能为空	
		0501	查询到多条符合条件的记录	
		0502	记录已存在	
		0503	记录不存在	
		1000	业务处理时错误	业务处理时错误
		9999	未知系统错误	未知系统错误
	 */
	private String STATUS;//报文状态
	private String ERRMSG;//错误消息
	private String RESERVED;//预留区域
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String version) {
		VERSION = version;
	}
	public String getSNDCODE() {
		return SNDCODE;
	}
	public void setSNDCODE(String sndcode) {
		SNDCODE = sndcode;
	}
	public String getRCVCODE() {
		return RCVCODE;
	}
	public void setRCVCODE(String rcvcode) {
		RCVCODE = rcvcode;
	}
	public String getMSGCODE() {
		return MSGCODE;
	}
	public void setMSGCODE(String msgcode) {
		MSGCODE = msgcode;
	}
	public String getSNDMSGID() {
		return SNDMSGID;
	}
	public void setSNDMSGID(String sndmsgid) {
		SNDMSGID = sndmsgid;
	}
	public String getUSERCODE() {
		return USERCODE;
	}
	public void setUSERCODE(String usercode) {
		USERCODE = usercode;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String username) {
		USERNAME = username;
	}

	
	public String getSNDTIME() {
		return SNDTIME;
	}
	public void setSNDTIME(String sNDTIME) {
		SNDTIME = sNDTIME;
	}
	public String getRCVTIME() {
		return RCVTIME;
	}
	public void setRCVTIME(String rCVTIME) {
		RCVTIME = rCVTIME;
	}
	public Integer getDATASIZE() {
		return DATASIZE;
	}
	public void setDATASIZE(Integer datasize) {
		DATASIZE = datasize;
	}
	public List<Data> getDATALIST() {
		return DATALIST;
	}
	public void setDATALIST(List<Data> datalist) {
		DATALIST = datalist;
	}
	public Integer getPAGENO() {
		return PAGENO;
	}
	public void setPAGENO(Integer pageno) {
		PAGENO = pageno;
	}
	public String getEOPSIGN() {
		return EOPSIGN;
	}
	public void setEOPSIGN(String eopsign) {
		EOPSIGN = eopsign;
	}
	public String getRCVMSGID() {
		return RCVMSGID;
	}
	public void setRCVMSGID(String rcvmsgid) {
		RCVMSGID = rcvmsgid;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
	public String getERRMSG() {
		return ERRMSG;
	}
	public void setERRMSG(String errmsg) {
		ERRMSG = errmsg;
	}
	public String getRESERVED() {
		return RESERVED;
	}
	public void setRESERVED(String reserved) {
		RESERVED = reserved;
	}
	
	
}

