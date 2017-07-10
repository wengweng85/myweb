package com.insigma.common.util;

public class StringFormatUtil {
	/**
	 * 邮箱地址转换
	 * @param email
	 * @return
	 */
	public static String getFormatStringForEmail(String email){
		String formatstr="";
		int idx=email.indexOf("@");
		if(idx!=-1){
			String subemail=email.substring(0,idx);
			String endemil=email.substring(idx);
			formatstr+=subemail.substring(0,subemail.length()/2);
			for (int i=0;i<subemail.length()-subemail.length()/2;i++){
				formatstr+="*";
			}
			formatstr+=endemil;
		}
		return formatstr;
	}

	/**
	 * 手机号码转换 隐藏中间4位手机号码
	 * @param mobile
	 * @return
	 */
	public static String getFormatStringForMobile(String mobile){
		String formatstr="";
		if(mobile.length()==11){
			String submobile=mobile.substring(0,3);
			String endmobile=mobile.substring(7,11);
			formatstr+=submobile;
			for (int i=0;i<4;i++){
				formatstr+="*";
			}
			formatstr+=endmobile;
		}
		return formatstr;
	}

	public static void main(String [] a){
		System.out.println(getFormatStringForEmail("wengshaohui@gmail.com"));
		System.out.println(getFormatStringForMobile("15958134215"));
	}
}
