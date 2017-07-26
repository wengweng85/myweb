package com.insigma.mvc.serviceimp.sysmanager.userrole;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.userrole.SysUserRoleMapper;
import com.insigma.mvc.model.SGroup;
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
	public AjaxReturnMsg getUserListByGroupid(SGroup sgroup) {
		PageHelper.startPage(sgroup.getCurpage(), sgroup.getLimit());
		List<SUser> list=sysUserRoleMapper.getUserListByGroupid(sgroup.getGroupid());
		PageInfo<SUser> pageinfo = new PageInfo<SUser>(list);
		return this.success(pageinfo);
	}

}
