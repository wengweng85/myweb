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

}
