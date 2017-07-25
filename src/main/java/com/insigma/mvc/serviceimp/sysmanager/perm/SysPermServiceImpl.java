package com.insigma.mvc.serviceimp.sysmanager.perm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.mvc.dao.sysmanager.perm.SysPermMapper;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.service.sysmanager.perm.SysPermService;


/**
 * 管理功能之权限管理service impl 
 * @author wengsh
 *
 */

@Service
public class SysPermServiceImpl implements SysPermService {

	//登录dao
	@Resource
	private SysPermMapper sysPermMapper;

	@Override
	public List<SPermission> getPermTreeList() {
		// TODO Auto-generated method stub
		return sysPermMapper.getPermTreeList();
	}

	@Override
	public SPermission getPermDataById(String id) {
		return sysPermMapper.getPermDataById(id);
	}

	@Override
	public void updatePermissionData(SPermission spermission) {
		sysPermMapper.updatePermissionData(spermission);
	}

	@Override
	public SPermission isPermCodeExist(SPermission spermission) {
		return sysPermMapper.isPermCodeExist(spermission);
	}

	@Override
	public void savePermissionData(SPermission spermission) {
		sysPermMapper.savePermissionData(spermission);
	}

	@Override
	public List<SPermission> getPermListDataByParentid(String parentid) {
		return sysPermMapper.getPermListDataByParentid(parentid);
	}

	@Override
	public void deletePermDataById(String id) {
		sysPermMapper.deletePermDataById(id);
	}

}
