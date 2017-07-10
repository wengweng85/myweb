package com.insigma.rpc.hessianservice;

import com.insigma.rpc.client.ServiceCall;
import com.insigma.rpc.infostruct.Message;
import com.insigma.rpc.infostructofsebao.Request;
import com.insigma.rpc.infostructofsebao.Response;

public class HessianServiceImpl implements HessianService {

	private ServiceCall servicecall;
	

	public ServiceCall getServicecall() {
		return servicecall;
	}

	public void setServicecall(ServiceCall servicecall) {
		this.servicecall = servicecall;
	}
	
	@Override
	public Message jk_test(Message requestmsg) {
		// TODO Auto-generated method stub
		return  servicecall.jk_test(requestmsg);
	}

	@Override
	public Response jk_test2(Request request) {
		// TODO Auto-generated method stub
		return  servicecall.jk_test2(request);
	}


}
