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

}
