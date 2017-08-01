package com.insigma.mvc.service.sysmanager.userrole;

import java.util.HashMap;
import java.util.List;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SGroup;
import com.insigma.mvc.model.SRole;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysUserRoleService {
	
    public List<SGroup>  getAllGroupList(String parentid);
    
    public AjaxReturnMsg getGroupDataById(String groupid);
	
	public HashMap<String,Object> getUserListByGroupid(SGroup sgroup);
	
	public HashMap<String,Object> getUserRoleByUserId(SRole srole);
	
	public AjaxReturnMsg saveUserRole(SRole srole);
	
}
