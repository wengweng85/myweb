package com.insigma.mvc.service.sysmanager.role;

import java.util.HashMap;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SRole;




/**
 * ������֮Ȩ�޹���service
 * @author wengsh
 *
 */
public interface SysRoleService {
	
    public HashMap<String,Object>  getAllRoleList( SRole role);
	
	public AjaxReturnMsg getRoleDataById(String id);
	
	public AjaxReturnMsg saveOrUpdateRoleData(SRole srole);
	
	public AjaxReturnMsg deleteRoleDataById(String id);
	
	public String getRolePermTreeData(String roleid);
	
	public AjaxReturnMsg saveRolePermData(SRole srole);

}
