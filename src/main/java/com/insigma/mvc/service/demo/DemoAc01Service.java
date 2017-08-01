package com.insigma.mvc.service.demo;

import java.util.HashMap;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Ac01;





/**
 * demo ac01 service
 * @author wengsh
 *
 */
public interface DemoAc01Service {
	
	public HashMap<String,Object> getAc01List( Ac01 ac01 );
	
	public AjaxReturnMsg deleteDemoById(String aac001);
	
	public AjaxReturnMsg batDeleteDemoData(Ac01 ac01);
	
	public Ac01 getDemoById(String aac001);
	
	public AjaxReturnMsg saveDemoData(Ac01 ac01);

}
