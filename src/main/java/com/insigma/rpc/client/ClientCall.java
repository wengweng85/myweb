package com.insigma.rpc.client;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.insigma.resolver.AppException;
import com.insigma.rpc.hessianservice.HessianService;
import com.insigma.rpc.infostruct.Data;
import com.insigma.rpc.infostruct.Message;
import com.insigma.rpc.infostruct.enums.BusCode;
import com.insigma.rpc.infostructofsebao.Head;
import com.insigma.rpc.infostructofsebao.Request;
import com.insigma.rpc.infostructofsebao.Response;

/**
 * 调用服务
 * 
 * @author wengsh
 *
 */
public class ClientCall {
	
	//基于hessian的远程服务调用地址
	private String HESSIAN_SERVICE_URL="http://127.0.0.1:8091/myweb/hessianService";  
	

	/**
	 * 查询个人信息212003
	 * 
	 * 目标系统通过此交易可以查询指定目标系统个人ID或姓名或身份证件号码对应的个人信息。
	 * 如果请求报文业务体中的目标系统个人ID不为空，则按照目标系统个人ID精确查找其映射的个人基本信息，没有找到则提示错误信息。
	 * 如果请求报文业务体中的目标系统个人ID为空，则按照姓名和身份证号来查找个人基本信息，姓名和身份证号必须有一个有值。
	 * 此交易可能返回多条符合条件的个人信息
	 * ，需目标系统自行判断选择哪条个人信息；如果响应报文中某条个人信息中的目标系统个人ID为空，则表示目标系统还未与该个人信息进行个人信息映射绑定
	 * ，目标系统可以调用交易5.2.2修改个人信息212002来实现个人信息映射绑定。 请求报文业务体记录数为1条，响应报文业务体记录数为多条。
	 * 
	 * @param BAC010  目标系统个人ID
	 * @param AAC002 证件号码
	 * @param AAC003 姓名
	 * @return
	 * @throws AppException
	 */
	public void jk_Test(JkDTO jkac01dto) throws AppException {
		Message message = new Message();
		List<Data> datalist = new ArrayList<Data>();
		Data data = new Data();
		// 如果目标系统个人ID不为空
		if (null != jkac01dto.getBac010() && !jkac01dto.getBac010().equals("")) {
			 data.setBAC010(jkac01dto.getBac010());//目标系统个人ID
		} else {
			throw new AppException("个人编号不能为空");
		}
		// aac002 以及aac003至少得有一个
		if (null != jkac01dto.getAac002() && !jkac01dto.getAac002().equals("")) {
			data.setAAC002(jkac01dto.getAac002());// 证件号码
		} else {
			throw new AppException("身份证号码不能为空");
		}
		// 姓名
		if (null != jkac01dto.getAac003() && !jkac01dto.getAac003().equals("")) {
			data.setAAC003(jkac01dto.getAac003());// 姓名
		} else {
			throw new AppException("姓名不能为空");
		}
		/*
		if (null != jkac01dto.getAac006() && !jkac01dto.getAac006().equals("")) {
			// data.setAAC006(jkac01dto.getAAC006());//出生日期
		} else {
			throw new AppException("出生日期不能为空");
		}
        */
		if (null != jkac01dto.getAac004() && !jkac01dto.getAac004().equals("")) {
			data.setAAC004(jkac01dto.getAac004());//性别
		} else {
			throw new AppException("性别不能为空");
		}
		/*
		if (null != jkac01dto.getAac005() && !jkac01dto.getAac005().equals("")) {
			// data.setAAC005(jkac01dto.getAAC005());//民族不能为空
		} else {
			throw new AppException("民族不能为空");
		}*/
		datalist.add(data);
		message.setDATALIST(datalist);
		message.setDATASIZE(1);
		Message returnmsg = call(message,BusCode.JK_212003);
		String status = returnmsg.getSTATUS();
		System.out.println(status);
			
	}

	/**
	 *个人参保信息查询（22KOW6）
	 *
	 * @return
	 * @throws AppException
	 */
	public Response jk_Test2(JkDTO dto) throws AppException{
		Request request=new Request();
		Head head=new Head();
		head.setAac002(dto.getAac002());
		request.setHead(head);
		Response returnmsg = call(request,BusCode.JK_22KOW6);
		return returnmsg;
	 }
	
	
	/**
	 * 基于hessian的接口调用
	 * @param requestmsg
	 * @param buscode
	 * @return
	 * @throws AppException
	 */
	private Message call(Message request,BusCode buscode) throws AppException {
        HessianProxyFactory factory = new HessianProxyFactory();  
        try {  
        	HessianService hessianservice = (HessianService) factory.create(HessianService.class, HESSIAN_SERVICE_URL);  
        	if((buscode.getCode()).equals(BusCode.JK_212003.getCode())){
        		 return hessianservice.jk_test(request);
        	}else{
        		throw new  AppException(buscode.getCode()+"接口类型不存在");
        	}
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            throw new  AppException("远程调用失败,请检查地址是否正确"+HESSIAN_SERVICE_URL);
        }  
	}
	
	/**
	 * 基于hessian的接口调用
	 * @param requestmsg
	 * @param buscode
	 * @return
	 * @throws AppException
	 */
	private Response call(Request requestmsg,BusCode buscode) throws AppException {
        HessianProxyFactory factory = new HessianProxyFactory();  
        try {  
        	HessianService hessianservice = (HessianService) factory.create(HessianService.class, HESSIAN_SERVICE_URL);  
        	if((buscode.getCode()).equals(BusCode.JK_22KOW6.getCode())){
        		 return hessianservice.jk_test2(requestmsg);
       	   }else{
        		throw new  AppException(buscode.getCode()+"接口类型不存在");
           }
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            throw new  AppException("远程调用失败,请检查地址是否正确"+HESSIAN_SERVICE_URL);
        }  
	}
	
}
