package com.insigma.common.ipparser;

/**
 * ip地址地区获取类
 * @author wengsh
 * @date 2014-11-21
 *
 */
public class IPSeekerUtil {
	
	/**
	 * 获取ip地址地区
	 * @param ipaddress
	 * @return
	 */
    public String getIpCountry(String ipaddress){
        if(!ipaddress.equals("")){
            IPSeeker ipseeker=new IPSeeker();
            return ipseeker.getIPLocation(ipaddress).getCountry();
        }else{
            return "未知地区";
        }

    }
    
   /**
    * 获取ip地址类型
    * @param ipaddress
    * @return
    */
    public  String getIpArea(String ipaddress){
        if(!ipaddress.equals("")) {
            IPSeeker ipseeker = new IPSeeker();
            return ipseeker.getIPLocation(ipaddress).getArea();
        }else{
            return "未知地区";
        }
    }
}
