package com.insigma.mvc.service.login;


import java.util.Set;

import com.insigma.mvc.model.SUser;
import com.insigma.resolver.AppException;


/**
 * 登录service接口
 * @author wengsh
 *
 */
public interface LoginService {
	/**
	 * 通过用户名获取会员表信息
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public SUser getUser(String loginname);
	
	/**
	 * 通过用户id获取用户角色集合
	 * @param userid
	 * @return 角色集合
	 * @throws Exception 
	 */
	public Set<String> findRolesStr(String loginname) throws AppException, Exception;
	
	/**
	 * 通过用户id获取用户权限集合
	 * @param userid
	 * @return 权限集合
	 * @throws Exception 
	 */
	public Set<String> findPermissionStr(String loginname) throws AppException, Exception;
	

}
