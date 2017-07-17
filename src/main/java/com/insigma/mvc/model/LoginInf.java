package com.insigma.mvc.model;

import java.util.Date;


/**
 *  ”√ªß±Ì
 * 
 */
public class LoginInf implements java.io.Serializable {
	
	
	private String loginhash;
	private Date logintime;
	private String ip;
	private String usergent;
	private String sessionid;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsergent() {
		return usergent;
	}
	public void setUsergent(String usergent) {
		this.usergent = usergent;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getLoginhash() {
		return loginhash;
	}
	public void setLoginhash(String loginhash) {
		this.loginhash = loginhash;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	
	
	

	
	
}
