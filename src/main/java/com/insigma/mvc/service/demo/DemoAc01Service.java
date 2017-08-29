package com.insigma.mvc.service.demo;

import java.util.HashMap;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.DemoAc01;





/**
 * demo ac01 service
 * @author wengsh
 *
 */
public interface DemoAc01Service {
	
	public HashMap<String,Object> getDemoAc01List( DemoAc01 ac01 );
	
	public AjaxReturnMsg<String> deleteDemoById(String aac001);
	
	public AjaxReturnMsg<String> batDeleteDemoData(DemoAc01 ac01);
	
	public DemoAc01 getDemoById(String aac001);
	
	public DemoAc01 getDemoNameById(String aac001); 
	
	public AjaxReturnMsg<String>saveDemoData(DemoAc01 ac01);
	
	public AjaxReturnMsg<String> updateDemoAc01DemBusUuid(DemoAc01 ac01);
	
	public AjaxReturnMsg<String> deletefile(DemoAc01 ac01);

}
