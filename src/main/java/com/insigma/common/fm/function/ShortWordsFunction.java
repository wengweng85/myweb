package com.insigma.common.fm.function;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义Freemarker方法<br>
 * <li>取得文字的缩略语</li>
 * 
 * @author zbq
 * @date 2010-7-21
 */
public class ShortWordsFunction implements TemplateMethodModel {

	public static final String DEFAULT_FILL_WORDS = "...";

	/**
	 * 将文字超长部分截取替换为特定字符<br>
	 * 参数顺序: 原文字、允许最大汉字数、截取后替换字符<br>
	 * 参数1、参数2必须存在、如果不存在返回空字符串、参数3不存在默认为"..."
	 */
	
	@SuppressWarnings("rawtypes")
	public Object exec(List paramList) throws TemplateModelException {
		return getWords(paramList);
	}

	/**
	 * 取得文字缩略语<br>
	 * <li>参数个数为2、3以外、返回空字符串</li>
	 * 
	 * @param paramList
	 * @return String
	 * @throws TemplateModelException
	 */
	@SuppressWarnings("rawtypes")
	private String getWords(List paramList) throws TemplateModelException {
		int paramCnt = paramList.size();
		if (paramCnt != 2 && paramCnt != 3) {
			throw new TemplateModelException("parameter count must be 2 or 3");
		}
		return getShortWords(paramList);
	}

	/**
	 * 根据最大字节长度对文字进行截取后添加缩略字符
	 */
	@SuppressWarnings("rawtypes")
	private String getShortWords(List paramList) {
		String src = (String) paramList.get(0); // 原文字
		int maxLength = Integer.parseInt(((String) paramList.get(1)));
		int maxByteCnt = maxLength;

		String fillWords = DEFAULT_FILL_WORDS;
		if (paramList.size() == 3) {
			fillWords = (String) paramList.get(2);
		}

		String result = getShortString(src, maxByteCnt, fillWords);
		return result;
	}

	/**
	 * 将字符转换为字节、取得最大长度字节后添加缩略字符
	 */
	private String getShortString(String str, int maxSize, String fillWords) {
		int length = str.length();
		if (length > maxSize) {
			str = str.substring(0, maxSize);
			str = mergeWords(str, fillWords);
		}
		return str;
	}

	/**
	 * 将字符转换为字节、取得最大长度字节后添加缩略字符
	 * 
	 * private String getShortString1(String str, int maxByteCnt, String
	 * fillWords) { byte[] byteArray = str.getBytes();
	 * 
	 * int size = byteArray.length; if (size > maxByteCnt) { str =
	 * byte2String(byteArray, maxByteCnt); str = mergeWords(str, fillWords); }
	 * return str; }
	 */
	/**
	 * 按字节截取后转换为字符串
	 * 
	 * private String byte2String(byte[] array, int length) { byte[] returnArray
	 * = new byte[length];
	 * 
	 * for (int i = 0; i < array.length; i++) { if (i > length - 1) { break; }
	 * returnArray[i] = array[i]; } return new String(returnArray); }
	 */
	
	
	/**
	 * 合并缩略文字和缩略字符<br>
	 * <li>文字 + ... => 文字...</li>
	 */
	private String mergeWords(String words, String mergeInfo) {
		StringBuffer newWords = new StringBuffer();
		newWords.append(words).append(mergeInfo);
		return newWords.toString();
	}
}
