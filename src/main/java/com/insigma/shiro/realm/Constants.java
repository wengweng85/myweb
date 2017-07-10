package com.insigma.shiro.realm;


/**
 * redis cache key get
 * @author wengsh
 *
 */
public class Constants {
	
	/**
	 * getUserModuleCacheKey
	 * @param username
	 * @return
	 */
	public static String getUserInfoKey(String username){
		return "user_info_key_"+username;
	}
	
	/**
	 * getUserRolesCacheKey
	 * @param username
	 * @return
	 */
	public static String getUserRolesCacheKey(String username){
		return "user_roles_key_"+username;
	}
	
	/**
	 * getUserRolesCacheKey
	 * @param username
	 * @return
	 */
	public static String getUserPermissionCacheKey(String username){
		return "user_permissions_key_"+username;
	}

}
