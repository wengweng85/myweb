package com.insigma.common.util;

import com.insigma.resolver.AppException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;

/***
 * JavaBean与HashMap处理类
 * @author kezp
 *
 */

public class BeanCopyUtil {
	
	/**
	 * 打印对象
	 * @param o
	 * @throws Exception
	 */
	public static void print(Object o)  throws Exception{
		BeanInfo beanInfo=Introspector.getBeanInfo(o.getClass(),Object.class);
		PropertyDescriptor[] prop=beanInfo.getPropertyDescriptors();
		for (int i=0;i<prop.length;i++){
			System.out.println("字段为:"+prop[i].getName()+",属性为："+prop[i].getPropertyType());
			System.out.println("其值为:"+prop[i].getReadMethod().invoke(o, new Object[] {}));
		}
	}
	
	/**
	 * 打印HashMap
	 * @param hm
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes", "unused" })
	public static void printHm(HashMap hm)  throws Exception{
		int size=hm.keySet().size();
		for (Object o : hm.keySet()) {
			System.out.println("名字为："+o.toString());
			System.out.println("其值为："+hm.get(o));
		}
	}
	

	/**
	 * 对象拷贝（单层模式）
	 * @param fromObj
	 * @param toObj
	 * @throws Exception
	 */
	public static void SingleCopy(Object fromObj,Object toObj) throws Exception {
		BeanInfo fromBean=Introspector.getBeanInfo(fromObj.getClass(),Object.class);
		BeanInfo toBean=Introspector.getBeanInfo(toObj.getClass(),Object.class);
		PropertyDescriptor[] fromprops=fromBean.getPropertyDescriptors();
		PropertyDescriptor[] toprops=toBean.getPropertyDescriptors();
		
		for (int i = 0; i < fromprops.length; i++){
			for (int j = 0; j < toprops.length; j++){
				//属性相同  类型相同
				if (fromprops[i].getName().equals(toprops[j].getName()) && fromprops[i].getPropertyType()==toprops[j].getPropertyType() ){
					Object fromValue=fromprops[i].getReadMethod().invoke(fromObj,new Object[] {});
					toprops[j].getWriteMethod().invoke(toObj, new Object[]{fromValue});
				}
			}
		}
	}
	
	/**
	 * 将对象拷贝到HashMap中
	 * @param fromObj
	 * @param hm
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void CopytoHashMap(Object fromObj,HashMap hm) throws Exception {
		BeanInfo fromBean=Introspector.getBeanInfo(fromObj.getClass(),Object.class);
		PropertyDescriptor[] fromprops=fromBean.getPropertyDescriptors();
		for (int i = 0; i < fromprops.length; i++){
			Object fromValue=fromprops[i].getReadMethod().invoke(fromObj,new Object[] {});
			String text="";
			if (fromprops[i].getPropertyType() == Date.class) {
				text = fromValue==null?"":DateUtil.formatDate((Date) fromValue);
			} else {
				text = fromValue==null?"":fromValue.toString();
			}
			hm.put(fromprops[i].getName(),text);
		}
	}
	
	/**
	 * 将HashMap拷贝到对象中
	 * @param Obj
	 * @param hm
	 * @throws AppException
	 */
	@SuppressWarnings("rawtypes")
	public static void CopyfromHashMap(Object obj,HashMap hm) throws Exception {
		CopyIgnoreProperty.copyHashMap(hm, obj);
	}
	
	
	
}
