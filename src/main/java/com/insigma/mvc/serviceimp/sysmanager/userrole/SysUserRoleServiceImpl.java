package com.insigma.mvc.serviceimp.sysmanager.userrole;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.userrole.SysUserRoleMapper;
import com.insigma.mvc.model.SGroup;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.sysmanager.userrole.SysUserRoleService;

/**
 * 用户角色管理
 * @author wengsh
 *
 */
@Service
public class SysUserRoleServiceImpl extends MvcHelper implements SysUserRoleService {

	@Resource
    private SysUserRoleMapper sysUserRoleMapper;
	
	
	/**
	 * 获取机构信息树
	 */
	@Override
	public String getAllGroupList(String parentid) {
		 String result= this.success_string_response(sysUserRoleMapper.getAllGroupList(parentid));
		 return result;
	}
	
	
	/**
	 * 获取机构信息
	 */
	@Override
	public AjaxReturnMsg getGroupDataById(String groupid) {
		return this.success(sysUserRoleMapper.getGroupDataById(groupid));
	}
	
	
	/**
	 * 获取机构下人员信息
	 */
	@Override
	public HashMap<String,Object> getUserListByGroupid(SGroup sgroup) {
		PageHelper.offsetPage(sgroup.getOffset(), sgroup.getLimit());
		List<SUser> list=sysUserRoleMapper.getUserListByGroupid(sgroup.getGroupid());
		PageInfo<SUser> pageinfo = new PageInfo<SUser>(list);
		return this.success_hashmap_response(pageinfo);
	}


	/**
	 * 通过人员id获取角色选中状态
	 */
	@Override
	public HashMap<String,Object> getUserRoleByUserId(SRole srole) {
		PageHelper.offsetPage(srole.getOffset(), srole.getLimit());
		List<SRole> list=sysUserRoleMapper.getUserRoleByUserId(srole.getUserid());
		PageInfo<SRole> pageinfo = new PageInfo<SRole>(list);
		return this.success_hashmap_response(pageinfo);
	}


	/**
	 * 保存用户角色
	 */
	@Override
	public AjaxReturnMsg saveUserRole(SRole srole) {
		sysUserRoleMapper.deleteUserRoleByUserId(srole.getUserid());
		String[] selectnodes= srole.getSelectnodes().split(",");
		for(int i=0;i<selectnodes.length;i++){
			SRole temp=new SRole();
			temp.setUserid(srole.getUserid());
			temp.setRoleid(selectnodes[i]);
			sysUserRoleMapper.saveUserRole(temp);
		}
		return this.success("用户授权成功");
	}

}
