package com.insigma.mvc.dao.sysmanager.role;

import java.util.List;

import com.insigma.mvc.model.SRole;


/**
 * 管理功能之角色管理
 * @author wengsh
 *
 */
public interface SysRoleMapper {
	
	public List<SRole> getAllRoleList();
	
	public SRole getRoleDataById(String id);
	
	public SRole isRoleCodeExist(SRole spermission);
	
	public SRole isRoleUsedbyUser(String roleid);
	
	public void saveRoleData(SRole spermission);
	
	public void updateRoleData(SRole spermission);
	
	public void deleteRoleDataById(String id);

}
