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
	
	public SRole isRoleCodeExist(SRole srole);
	
	public SRole isRoleUsedbyUser(String roleid);
	
	public int saveRoleData(SRole spermission);
	
	public int updateRoleData(SRole spermission);
	
	public int deleteRoleDataById(String id);
	
	public  List<SRole> getRolePermTreeData(String roleid);
	
	public int deleteRolePermbyRoleid(String roleid);
	
	public int saveRolePermData(SRole srole);

}
