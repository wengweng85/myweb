package com.insigma.mvc.service.sysmanager.role;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SRole;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysRoleService {
	
    public AjaxReturnMsg  getAllRoleList( SRole role);
	
	public AjaxReturnMsg getRoleDataById(String id);
	
	public AjaxReturnMsg saveOrUpdateRoleData(SRole srole);
	
	public AjaxReturnMsg deleteRoleDataById(String id);
	
	public String getRolePermTreeData(String roleid);
	
	public AjaxReturnMsg saveRolePermData(SRole srole);

}
