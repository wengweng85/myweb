package com.insigma.common.util;

import org.springframework.util.StringUtils;

/**
 * 字符处理类
 * @author kezp
 *
 */
public class StringUtil {

	/**
	 * 是否特殊字符
	 * @param cn
	 * @return
	 */
	public static boolean isValidChar(char ch) {
		int hightByte;
		int lowByte;
		byte[] bytes = (String.valueOf(ch)).getBytes();
		if (bytes.length > 2) { // 错误
			return false;
		}
		if (bytes.length == 1) { // 英文字符
			hightByte = bytes[0];
			if ((hightByte >= 32) && (hightByte <= 126)) {
				return true;
			} else {
				return false;
			}
		}
		if (bytes.length == 2) { // 中文字符
			hightByte = bytes[0] & 0xff;
			lowByte = bytes[1] & 0xff;
			if ((hightByte >= 129 && hightByte <= 254)
					&& (lowByte >= 64 && lowByte <= 254)) { //81 40 - FE FE 转成ascii为： 129 64 - 254 254
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 得到字符的ascii，包括汉字
	 * @param ch
	 * @return
	 */
	public static int getAscii(char ch) {
		int ascii = 0;
		int hightByte;
		int lowByte;
		byte[] bytes = (String.valueOf(ch)).getBytes();

		if (bytes.length == 1) { // 英文字符
			ascii = bytes[0];
		}
		if (bytes.length == 2) { // 中文字符
			hightByte = bytes[0] & 0xff;
			lowByte = bytes[1] & 0xff;
			ascii = hightByte * 256 + lowByte;
		}
		return ascii;
	}

	/**
	 * 过滤xml的特殊字符
	 * @param val
	 * @return
	 */
	public static String encodeXML(String xmlString) {
		//&lt; <  小于号
		//&gt; > 大于号
		//&amp; &  和
		//&apos; ' 单引号
		//&quot; " 双引号
		xmlString = xmlString.replaceAll("&lt;", "<");
		xmlString = xmlString.replaceAll("&gt;", ">");
		xmlString = xmlString.replaceAll("&amp;", "&");
		xmlString = xmlString.replaceAll("&apos;", "'");
		xmlString = xmlString.replaceAll("&quot;", "\"");

		char ch;
		String str;
		StringBuffer buf = new StringBuffer();
		//其他特殊字符
		for (int i = 0; i < xmlString.length(); i++) {
			ch = xmlString.charAt(i);
			if (!isValidChar(ch)) { //检查是否特殊字符串
				continue;
			}
			//str = String.valueOf(ch);
			str = Character.toString(ch);
			if ('<' == ch) {
				str = "&lt;";
			}
			if ('>' == ch) {
				str = "&gt;";
			}
			if ('&' == ch) {
				str = "&amp;";
			}
			if ('\'' == ch) {
				str = "&apos;";
			}
			if ('"' == ch) {
				str = "&quot;";
			}
			buf.append(str);
		}
		return buf.toString();
	}
	
	/**
	 * 处理文件路径
	 * @param pathString
	 * @return
	 */
	public static String resolvePath(String pathString) {
		String strpath = StringUtils.replace(pathString,"\\", "/");
		if (!strpath.endsWith("/")){
			strpath = strpath +"/";
		}
		return strpath;
	}
	
	/**
	 * 字符串倒写
	 * @param str
	 * @return
	 */
	public static String reverse(String str){
		StringBuffer sb = new StringBuffer(str);
		return sb.reverse().toString();
	}
	
	
	/**
	 * 测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		/*************查Ascii码***********************/
		char c1 ='我';
		char c2 ='a';
		System.out.println(getAscii(c1));
		System.out.println(getAscii(c2));
		char c3 ='	';  //tab 符号
		System.out.println(getAscii(c3));
		char c4 ='\"';  //双引号
		System.out.println(getAscii(c4));
		/*********************************************/
		
		/*************查特殊字段*************************/
		System.out.println(isValidChar(c3));
		System.out.println(isValidChar(c4));
		/**********************************************/
		
		/*************替换XML中的特殊字符******************/
		String strxml = encodeXML("fasdf<,xm;>,\\");
		System.out.println(strxml);
		/**********************************************/
		
		/**********把c:\kezp\test\替换为******c:/kezp/test/*****************/
		System.out.println(resolvePath("c:\\kezp\\ada"));
		System.out.println(reverse("c:\\kezp\\ada"));
		/**********************************************/

	}

}
