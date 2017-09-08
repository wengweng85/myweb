package com.insigma.mvc.service.sysmanager.perm;

import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SPermission;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysPermService {
	
	public List<SPermission>  getPermTreeList();
	
	public AjaxReturnMsg<SPermission> getPermDataById(String id);
	
	public AjaxReturnMsg<String> saveOrUpdatePermData(SPermission spermission);
	
	public AjaxReturnMsg<String> deletePermDataById(String id);
	
	public AjaxReturnMsg<String> moveNode(String id);

}
