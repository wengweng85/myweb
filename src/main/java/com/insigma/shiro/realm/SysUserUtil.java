package com.insigma.shiro.realm;

import com.insigma.mvc.model.SUser;

/**
 * 系统工具类
 * @author wengsh
 *
 */
public class SysUserUtil {
	
	public static final String SHIRO_CURRENT_USER_INFO="SHIRO_CURRENT_USER_INFO";
	
	private static ThreadLocal<SUser> local = new ThreadLocal<SUser>();  
	  
    public static void setCurrentUser(SUser suser) {  
        local.set(suser);  
    }  
  
    public static SUser getCurrentUser() {  
        return local.get();  
    }  

}
