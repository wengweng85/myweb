package com.insigma.mvc.service.sysmanager.role;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysRoleService {
	
    public PageInfo<SRole>  getAllRoleList( SRole role);
	
	public SRole getRoleDataById(String id);
	
	public SRole isRoleCodeExist(SRole srole);
	
	public void saveRoleData(SRole srole);
	
	public void updateRoleData(SRole srole);
	
	public void deleteRoleDataById(String id);
	
	public SRole isRoleUsedbyUser(String roleid);

}
