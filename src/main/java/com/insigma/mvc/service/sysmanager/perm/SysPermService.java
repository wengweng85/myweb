package com.insigma.mvc.service.sysmanager.perm;

import java.util.List;

import com.insigma.mvc.model.SPermission;




/**
 * 管理功能之权限管理service
 * @author wengsh
 *
 */
public interface SysPermService {
	
	public List<SPermission> getPermTreeList();
	
	public SPermission getPermDataById(String id);

}
