package com.insigma.common.ipparser;

public class IPLocation {
	/**
	 * 
	 */
	private final IPSeeker IPLocation;
	private String country;
	private String area;
	
	public IPLocation(IPSeeker ipSeeker) {
	    IPLocation = ipSeeker;
		country = area = "";
	}
	
	public IPLocation getCopy() {
	    IPLocation ret = new IPLocation(IPLocation);
	    ret.country = country;
	    ret.area = area;
	    return ret;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}