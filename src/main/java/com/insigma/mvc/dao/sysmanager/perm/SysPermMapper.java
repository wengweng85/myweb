package com.insigma.mvc.dao.sysmanager.perm;

import java.util.List;

import com.insigma.mvc.model.SPermission;


/**
 * ������֮Ȩ�޹���
 * @author wengsh
 *
 */
public interface SysPermMapper {
	
	public List<SPermission> getPermTreeList();
	
	public SPermission getPermDataById(String id);
	
	public SPermission isPermCodeExist(SPermission spermission);
	
	public void savePermissionData(SPermission spermission);
	
	public void updatePermissionData(SPermission spermission);
	
	public List<SPermission> getPermListDataByParentid(String parentid);
	
	public void deletePermDataById(String id);
	
	

}
