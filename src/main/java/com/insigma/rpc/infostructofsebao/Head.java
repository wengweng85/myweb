package com.insigma.rpc.infostructofsebao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 用于社保查询接口
 * @author wengsh
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Head implements java.io.Serializable {
	private String aac002;
	private String year;
	private String aae140;
	private String pagesize;
	private String pageindex;
	private String rc;
	private String rm;
	public String getRc() {
		return rc;
	}
	public void setRc(String rc) {
		this.rc = rc;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAae140() {
		return aae140;
	}
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getPageindex() {
		return pageindex;
	}
	public void setPageindex(String pageindex) {
		this.pageindex = pageindex;
	}
	
}
