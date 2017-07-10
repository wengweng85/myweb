package com.insigma.rpc.client.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insigma.resolver.AppException;
import com.insigma.rpc.client.ClientCall;
import com.insigma.rpc.client.JkDTO;

/**
 * junit≤‚ ‘
 * @author Administrator
 *
 */
public class TestJkTest {
	
	@Before
	public void before(){
		System.out.println("@before");
	}
	
	@Test
	public void test(){
		try{
			JkDTO dto=new JkDTO();
		    dto.setBac010("1000048479");
			//dto.setAac001("0000000000485233");
			dto.setAac002("330104196212213346");
			dto.setAac003("–Ï∑Ô’‰5");
			dto.setAac004("01");//–‘±
			new ClientCall().jk_Test(dto);
		}catch(AppException e){
			e.printStackTrace();
		}
	}
	
	@After
	public void after(){
		System.out.println("@After");
	}

}
