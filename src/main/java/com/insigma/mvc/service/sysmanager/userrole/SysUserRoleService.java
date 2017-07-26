package com.insigma.mvc.service.sysmanager.userrole;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SGroup;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysUserRoleService {
	
    public String  getAllGroupList(String parentid);
    
    public AjaxReturnMsg getGroupDataById(String groupid);
	
	public AjaxReturnMsg getUserListByGroupid(SGroup sgroup);
	
	
}
