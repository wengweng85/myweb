package com.insigma.rpc.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * MyWebService
 * @author wengsh
 *
 */
@WebService 
@SOAPBinding(style = Style.RPC)
public interface MyWebService 
{ 
	int add(@WebParam(name = "firstA")int a, @WebParam(name = "firstB")int b); 
    int minus(@WebParam(name = "secondA")int a, @WebParam(name = "secondB")int b); 
}

