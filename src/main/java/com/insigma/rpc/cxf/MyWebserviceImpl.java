package com.insigma.rpc.cxf;


/**
 * 接口实现
 * @author wengsh
 *
 */
public class MyWebserviceImpl implements MyWebService { 
	@Override public int add(int a, int b) { 
		System.out.println(a+"+"+b+"="+(a+b)); return a+b; 
	} 
	@Override 
	public int minus(int a, int b) { 
		System.out.println(a+"-"+b+"="+(a-b)); return a-b; 
	} 
	
	//后续增加数据标准
}
