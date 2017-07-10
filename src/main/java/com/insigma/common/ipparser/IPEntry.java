package com.insigma.common.ipparser;

public class IPEntry {  
    /**
	 * 
	 */
	@SuppressWarnings("unused")
	private IPSeeker IpEntry;
	public String beginIp;  
    public String endIp;  
    public String country;  
    public String area;  
      
    /** 
     * ¹¹Ôìº¯Êý 
     * @param ipSeeker TODO
     */  
    public IPEntry(IPSeeker ipSeeker) {  
        IpEntry = ipSeeker;
		beginIp = endIp = country = area = "";  
    }  
}