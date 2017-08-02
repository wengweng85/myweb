package com.insigma.mvc.service.resources.SXJY_RLZYSC_002_001;

import java.util.HashMap;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.model.Ef11;





/**
 * ef11 service
 * @author pangyy
 *
 */
public interface HRAgencyApplyService {
	
	public HashMap<String,Object> getAc01List( Ac01 ac01 );
	
	public AjaxReturnMsg deleteDemoById(String aac001);
	
	public AjaxReturnMsg batDeleteDemoData(Ac01 ac01);
	
	public AjaxReturnMsg getDemoById(String aac001);
	
	public AjaxReturnMsg saveEf11Data(Ef11 ef11);

}
