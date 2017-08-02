package com.insigma.mvc.model;

import java.util.Date;

public class Ec13 {
    private String eec131;

	private String eec121;

	private String aec104;

	private Date aec105;

	private String aab301;

	public String getEec131() {
		return eec131;
	}

	public void setEec131(String eec131) {
		this.eec131 = eec131 == null ? null : eec131.trim();
	}

	public String getEec121() {
		return eec121;
	}

	public void setEec121(String eec121) {
		this.eec121 = eec121 == null ? null : eec121.trim();
	}

	public String getAec104() {
		return aec104;
	}

	public void setAec104(String aec104) {
		this.aec104 = aec104 == null ? null : aec104.trim();
	}

	public Date getAec105() {
		return aec105;
	}

	public void setAec105(Date aec105) {
		this.aec105 = aec105;
	}

	public String getAab301() {
		return aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301 == null ? null : aab301.trim();
	}

}