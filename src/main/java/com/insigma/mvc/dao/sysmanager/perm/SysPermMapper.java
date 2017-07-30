package com.insigma.mvc.dao.sysmanager.perm;

import java.util.List;

import com.insigma.mvc.model.SPermission;


/**
 * 管理功能之权限管理
 * @author wengsh
 *
 */
public interface SysPermMapper {
	
	public List<SPermission> getPermTreeList();
	
	public SPermission getPermDataById(String id);
	
	public SPermission isPermCodeExist(SPermission spermission);
	
	public int savePermissionData(SPermission spermission);
	
	public int updatePermissionData(SPermission spermission);
	
	public List<SPermission> getPermListDataByParentid(String parentid);
	
	public int deletePermDataById(String id);
	
	

}
