package com.insigma.rpc.hessianservice;

import com.insigma.rpc.infostruct.Message;
import com.insigma.rpc.infostructofsebao.Request;
import com.insigma.rpc.infostructofsebao.Response;

public interface HessianService {
	
	public Message jk_test( Message  requestmsg);
	public Response jk_test2( Request  request);
	
	
}
