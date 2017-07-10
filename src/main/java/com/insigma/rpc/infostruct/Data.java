package com.insigma.rpc.infostruct;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.insigma.rpc.xml.JaxbDateAdapter;
import com.insigma.rpc.xml.JaxbDateTimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data  implements java.io.Serializable{
	private String DUSERID;//目标系统用户ID
    private String USERID;//用户ID
    private String NAME;//姓名
    private String IDNO;//身份证号码
    private String ERRMSG;//错误信息
    
    private String CGTYPE;//变更类型 1-新增，2-修改，3-删除
    private String CGTIME ;//变更时间;
    private String SIZE ;//修改项条数
    
    private String NDID;//节点机构ID
    private String DNDID;//目标系统节点机构ID;
    private String PNDID;//父节点机构ID
    private String DPNDID;//父节点机构映射的目标系统节点机构父节点ID
    
    private String BAC010;//目标系统个人ID
    private String AAC002;//证件号码
    private String AAC003;//姓名
    
	private String 	AAC001;//个人ID 
	
	@XmlJavaTypeAdapter(JaxbDateAdapter.class) 
	private Date STARTDATE;//开始时间
	@XmlJavaTypeAdapter(JaxbDateAdapter.class) 
	private Date ENDDATE;//结束时间
	private String AAE302;//发起变更的系统代码
	@XmlJavaTypeAdapter(JaxbDateTimeAdapter.class) 
	private Date AAE036;//变更日期;
	
	


	@XmlJavaTypeAdapter(JaxbDateAdapter.class) 
    private Date  AAC006 ;//出生日期
    private String  AAC004	;//性别
    private String  AAC005	;//民族	
    private String  BAC001;//籍贯
    private String  AAC161;//国籍
    private String  AAC009	;//户籍性质	
    private String  AAC700	;//户口簿号码
   	private String  BAC004	;//户主姓名
   	private String  AAC069	;//与户主关系
   	private String  BAC006;//家庭成员人数
   	private String  AAC010	;//户籍所在地
    private String  BAC007	;//户籍所在区
    private String  BAC008	;//户籍所在街道
    private String  BAC009	;//户籍所在社区
    private String  AAC017;//婚姻状况
    private String  BAC003	;//政治面貌
    private String  AAC153;//是否单亲
    private String  AAC154	;//是否独居
    private String  AAC086;//是否孤寡或孤儿
    private String  AAC011	;//学历
   	private String  AAC155	;//技术职称
   	private String  AAC156	;//是否城镇三无人员
   	private String  AAE008;//现居住地址
   	private String  AAE009	;//现居住地址邮编
    private String  AAE004	;//联系电话
    private String  AAE159	;//个人电子邮箱
    @XmlJavaTypeAdapter(JaxbDateAdapter.class) 
    private Date  AAC157	;//入伍时间
    @XmlJavaTypeAdapter(JaxbDateAdapter.class) 
    private Date  AAC158	;//	转业时间
    private String  AAC159	;//转业时所任部队职级
    
    
    
    public String getBAC010() {
		return BAC010;
	}

	public void setBAC010(String bAC010) {
		BAC010 = bAC010;
	}

	public String getAAC002() {
		return AAC002;
	}

	public void setAAC002(String aAC002) {
		AAC002 = aAC002;
	}

	public String getAAC003() {
		return AAC003;
	}

	public void setAAC003(String aAC003) {
		AAC003 = aAC003;
	}

	public String getAAC001() {
		return AAC001;
	}

	public void setAAC001(String aAC001) {
		AAC001 = aAC001;
	}


	public Date getSTARTDATE() {
		return STARTDATE;
	}

	public void setSTARTDATE(Date sTARTDATE) {
		STARTDATE = sTARTDATE;
	}

	public Date getENDDATE() {
		return ENDDATE;
	}

	public void setENDDATE(Date eNDDATE) {
		ENDDATE = eNDDATE;
	}

	public String getAAE302() {
		return AAE302;
	}

	public void setAAE302(String aAE302) {
		AAE302 = aAE302;
	}

	public Date getAAE036() {
		return AAE036;
	}

	public void setAAE036(Date aAE036) {
		AAE036 = aAE036;
	}
	
	
	public Date getAAC006() {
		return AAC006;
	}

	public void setAAC006(Date aAC006) {
		AAC006 = aAC006;
	}

	public Date getAAC157() {
		return AAC157;
	}

	public void setAAC157(Date aAC157) {
		AAC157 = aAC157;
	}

	public Date getAAC158() {
		return AAC158;
	}

	public void setAAC158(Date aAC158) {
		AAC158 = aAC158;
	}

	public String getAAC004() {
		return AAC004;
	}

	public void setAAC004(String aAC004) {
		AAC004 = aAC004;
	}

	public String getAAC005() {
		return AAC005;
	}

	public void setAAC005(String aAC005) {
		AAC005 = aAC005;
	}

	public String getBAC001() {
		return BAC001;
	}

	public void setBAC001(String bAC001) {
		BAC001 = bAC001;
	}

	public String getAAC161() {
		return AAC161;
	}

	public void setAAC161(String aAC161) {
		AAC161 = aAC161;
	}

	public String getAAC009() {
		return AAC009;
	}

	public void setAAC009(String aAC009) {
		AAC009 = aAC009;
	}

	public String getAAC700() {
		return AAC700;
	}

	public void setAAC700(String aAC700) {
		AAC700 = aAC700;
	}

	public String getBAC004() {
		return BAC004;
	}

	public void setBAC004(String bAC004) {
		BAC004 = bAC004;
	}

	public String getAAC069() {
		return AAC069;
	}

	public void setAAC069(String aAC069) {
		AAC069 = aAC069;
	}

	public String getBAC006() {
		return BAC006;
	}

	public void setBAC006(String bAC006) {
		BAC006 = bAC006;
	}

	public String getAAC010() {
		return AAC010;
	}

	public void setAAC010(String aAC010) {
		AAC010 = aAC010;
	}

	public String getBAC007() {
		return BAC007;
	}

	public void setBAC007(String bAC007) {
		BAC007 = bAC007;
	}

	public String getBAC008() {
		return BAC008;
	}

	public void setBAC008(String bAC008) {
		BAC008 = bAC008;
	}

	public String getBAC009() {
		return BAC009;
	}

	public void setBAC009(String bAC009) {
		BAC009 = bAC009;
	}

	public String getAAC017() {
		return AAC017;
	}

	public void setAAC017(String aAC017) {
		AAC017 = aAC017;
	}

	public String getBAC003() {
		return BAC003;
	}

	public void setBAC003(String bAC003) {
		BAC003 = bAC003;
	}

	public String getAAC153() {
		return AAC153;
	}

	public void setAAC153(String aAC153) {
		AAC153 = aAC153;
	}

	public String getAAC154() {
		return AAC154;
	}

	public void setAAC154(String aAC154) {
		AAC154 = aAC154;
	}

	public String getAAC086() {
		return AAC086;
	}

	public void setAAC086(String aAC086) {
		AAC086 = aAC086;
	}

	public String getAAC011() {
		return AAC011;
	}

	public void setAAC011(String aAC011) {
		AAC011 = aAC011;
	}

	public String getAAC155() {
		return AAC155;
	}

	public void setAAC155(String aAC155) {
		AAC155 = aAC155;
	}

	public String getAAC156() {
		return AAC156;
	}

	public void setAAC156(String aAC156) {
		AAC156 = aAC156;
	}

	public String getAAE008() {
		return AAE008;
	}

	public void setAAE008(String aAE008) {
		AAE008 = aAE008;
	}

	public String getAAE009() {
		return AAE009;
	}

	public void setAAE009(String aAE009) {
		AAE009 = aAE009;
	}

	public String getAAE004() {
		return AAE004;
	}

	public void setAAE004(String aAE004) {
		AAE004 = aAE004;
	}

	public String getAAE159() {
		return AAE159;
	}

	public void setAAE159(String aAE159) {
		AAE159 = aAE159;
	}

	public String getAAC159() {
		return AAC159;
	}

	public void setAAC159(String aAC159) {
		AAC159 = aAC159;
	}
    
    public String getNDID() {
		return NDID;
	}

	public void setNDID(String nDID) {
		NDID = nDID;
	}

	public String getDNDID() {
		return DNDID;
	}

	public void setDNDID(String dNDID) {
		DNDID = dNDID;
	}

	public String getPNDID() {
		return PNDID;
	}

	public void setPNDID(String pNDID) {
		PNDID = pNDID;
	}

	public String getDPNDID() {
		return DPNDID;
	}

	public void setDPNDID(String dPNDID) {
		DPNDID = dPNDID;
	}

	@XmlElementWrapper(name="ROWS")
	@XmlElement(name="ROW")
    private List<Row> ROWS;
	public String getERRMSG() {
		return ERRMSG;
	}

	public void setERRMSG(String errmsg) {
		ERRMSG = errmsg;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String userid) {
		USERID = userid;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getIDNO() {
		return IDNO;
	}

	public void setIDNO(String idno) {
		IDNO = idno;
	}

	public String getDUSERID() {
		return DUSERID;
	}

	public  void setDUSERID(String duserid) {
		DUSERID = duserid;
	}

	public String getCGTYPE() {
		return CGTYPE;
	}

	public void setCGTYPE(String cgtype) {
		CGTYPE = cgtype;
	}

	public String getCGTIME() {
		return CGTIME;
	}

	public void setCGTIME(String cgtime) {
		CGTIME = cgtime;
	}

	public String getSIZE() {
		return SIZE;
	}

	public void setSIZE(String size) {
		SIZE = size;
	}

	public List<Row> getROWS() {
		return ROWS;
	}

	public void setROWS(List<Row> rows) {
		ROWS = rows;
	}

}
