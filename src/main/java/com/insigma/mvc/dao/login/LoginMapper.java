package com.insigma.mvc.dao.login;


import java.util.List;

import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.resolver.AppException;


/**
 * 登录service接口
 * @author wengsh
 *
 */
public interface LoginMapper {
	/**
	 * 通过用户名获取会员表信息
	 * @param username
	 * @return 用户表
	 * @throws Exception 
	 */
	public SUser getUserAndGroupInfo(String loginname)  ;
	
	/**
	 * 通过用户id获取用户角色集合
	 * @param userid
	 * @return 角色集合
	 * @throws Exception 
	 */
	public List<SRole> findRolesStr(String loginname) ;
	
	
	/**
	 * 通过用户id获取用户权限集合
	 * @param userid
	 * @return 权限集合
	 * @throws Exception 
	 */
	public List<SPermission> findPermissionStr(String loginname) ;
	
	
	/**
	 * 保存hashinfo
	 * @param hashinfo
	 */
	public void saveLoginHashInfo(LoginInf inf);
	
	
	public List<LoginInf> findLoginInfoByhashcode(String loginhash);

}
