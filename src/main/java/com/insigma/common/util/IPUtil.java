package com.insigma.common.util;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * ip地址工具类
 * @author wengsh
 * @date 2012-4-28
 */
public class IPUtil {

	/**
	 * 得到操作系统主机ip
	 * <p>
	 * 通过此方法可以通用<code>NetworkInterface</code>的<code>getNetworkInterfaces</code>得到操作系统所有网络接口信息
	 * 但由于一个网络接口上可以绑定多个ip地址,需要对所有的ip地址做筛选,通常<code>127.0.0.1</code>的地址要过滤。
	 * <p>
	 * 在没有<code>192</code>开头的ip地址存在的情况下,非<code>192</code>的地址是首选,其次才是以<code>192</code>开头的地址
	 * @return ip地址 <code>String<code>
	 */
	public static String getInetAddress() {
		List<String> ip = new ArrayList<String>();
		List<String> ipother = new ArrayList<String>();
		Enumeration<NetworkInterface> nis = null;//网络接口类
		try {
			nis = NetworkInterface.getNetworkInterfaces(); //得到所有的的网络接口
			while (nis.hasMoreElements()) {
				NetworkInterface ni = nis.nextElement();
				Enumeration<InetAddress> ias = ni.getInetAddresses();//得到所有的InetAddress
				while (ias.hasMoreElements()) {
					InetAddress ia = ias.nextElement();
					if (ia instanceof Inet4Address
							&& !ia.getHostAddress().equals("127.0.0.1")) {
						//System.out.println("--操作系统主机IPV4之一--【" + ia.getHostAddress() + "】");//操作系统主机ip
						//以192开头的ip通常是内部使用ip
						if (!ia.getHostAddress().startsWith("192")) {
							ip.add(ia.getHostAddress());
						} else {
							ipother.add(ia.getHostAddress());
						}
					} else if (ia instanceof Inet6Address && !ias.equals("")) {
						//System.out.println("--操作系统主机IPV6之一--【" + ia.getHostAddress() + "】");//操作系统主机ip
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ip.size() == 0 && ipother.size() != 0) {
			return ipother.get(0);
		} else if (ip.size() > 0) {
			return ip.get(0);
		} else {
			return "";
		}
	}
    /**
     * 得到客户端访问ip地址
     * @param request
     * @return
     */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip==null){
			ip="";
		}

		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

	/**
	 * 主类
	 * @param a
	 */
	public static void main(String[] a) {
		System.out.println(getInetAddress());
	}
}
