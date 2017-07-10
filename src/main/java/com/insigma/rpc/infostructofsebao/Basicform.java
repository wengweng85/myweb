package com.insigma.rpc.infostructofsebao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * xml转换类,用于社保查询接口
 * @author wengsh
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Basicform  implements java.io.Serializable {
	private String aac003;//姓名
	private String aac002;//身份证号
	private String aac999;//个人编号;
	private String aac004;//性别
	private String aac005;//民族（
	private String aac006;//出生日期
	private String aac007;//参加工作日期	
	private String aac084;//离退休标识
	private String akc021;//医疗人员类别
	private String aac024;//政治面貌
	private String aac009;//户口性质
	private String aac010;//户口所在地详细地址
	private String aac011;//文化程度
	private String aac013;//用工形式
	public String getAac003() {
		return aac003;
	}
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getAac999() {
		return aac999;
	}
	public void setAac999(String aac999) {
		this.aac999 = aac999;
	}
	public String getAac004() {
		return aac004;
	}
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}
	public String getAac005() {
		return aac005;
	}
	public void setAac005(String aac005) {
		this.aac005 = aac005;
	}
	public String getAac006() {
		return aac006;
	}
	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}
	public String getAac007() {
		return aac007;
	}
	public void setAac007(String aac007) {
		this.aac007 = aac007;
	}
	public String getAac084() {
		return aac084;
	}
	public void setAac084(String aac084) {
		this.aac084 = aac084;
	}
	public String getAkc021() {
		return akc021;
	}
	public void setAkc021(String akc021) {
		this.akc021 = akc021;
	}
	public String getAac024() {
		return aac024;
	}
	public void setAac024(String aac024) {
		this.aac024 = aac024;
	}
	public String getAac009() {
		return aac009;
	}
	public void setAac009(String aac009) {
		this.aac009 = aac009;
	}
	public String getAac010() {
		return aac010;
	}
	public void setAac010(String aac010) {
		this.aac010 = aac010;
	}
	public String getAac011() {
		return aac011;
	}
	public void setAac011(String aac011) {
		this.aac011 = aac011;
	}
	public String getAac013() {
		return aac013;
	}
	public void setAac013(String aac013) {
		this.aac013 = aac013;
	}
					

	
}
