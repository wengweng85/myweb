package com.insigma.mvc.serviceimp.sysmanager.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.mvc.dao.sysmanager.role.SysRoleMapper;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.service.sysmanager.role.SysRoleService;


/**
 * 管理功能之角色管理service impl 
 * @author wengsh
 *
 */

@Service
public class SysRoleServicelmpl implements SysRoleService {

	//登录dao
	@Resource
	private SysRoleMapper sysRoleMapper;

	@Override
	public PageInfo<SRole> getAllRoleList( SRole srole) {
		PageHelper.startPage(srole.getCurpage(), srole.getLimit());
		// TODO Auto-generated method stub
		List<SRole> list=sysRoleMapper.getAllRoleList();
		PageInfo<SRole> pageinfo = new PageInfo<SRole>(list);
		return pageinfo;
	}
 
	@Override
	public SRole getRoleDataById(String id) {
		// TODO Auto-generated method stub
		return sysRoleMapper.getRoleDataById(id);
	}

	@Override
	public SRole isRoleCodeExist(SRole srole) {
		// TODO Auto-generated method stub
		return sysRoleMapper.isRoleCodeExist(srole);
	}

	@Override
	public void saveRoleData(SRole srole) {
		sysRoleMapper.saveRoleData(srole);
	}

	@Override
	public void updateRoleData(SRole srole) {
		sysRoleMapper.updateRoleData(srole);
	}

	@Override
	public void deleteRoleDataById(String id) {
		sysRoleMapper.deleteRoleDataById(id);
	}

	@Override
	public SRole isRoleUsedbyUser(String roleid) {
		return sysRoleMapper.isRoleUsedbyUser(roleid);
	}
	
}
