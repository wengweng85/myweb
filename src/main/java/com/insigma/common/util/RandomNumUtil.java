package com.insigma.common.util;

import java.util.Random;

public class RandomNumUtil{

	/**
	 * 生成验证码
	 * @param size
	 * @return
	 */
	public static String getRandomNum(int size){
		String sid="";
		for(int i=0;i<size;i++){
			int random=(int)(Math.random()*10);
			sid += random;
		}
		return sid;
	}

	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static void main(String [] a){
		System.out.println(getRandomNum(6));
	}
}
