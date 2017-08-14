package com.insigma.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

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
    
    /**
     * 更新当前用户权限,系统内对用户做了权限修改后可以通过调用此方式动态修改当前用户权限
     */
   public static void updateCurrentUserPerms(){
		Subject subject = SecurityUtils.getSubject(); 
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();  
		MyShiroRealm shiroRealm = (MyShiroRealm)rsm.getRealms().iterator().next();  
		String realmName = subject.getPrincipals().getRealmNames().iterator().next(); 
		//第一个参数为用户名,第二个参数为realmName
		SimplePrincipalCollection principals = new SimplePrincipalCollection(SysUserUtil.getCurrentUser().getUsername(),realmName); 
		subject.runAs(principals); 
		shiroRealm.getAuthorizationCache().remove(subject.getPrincipals()); 
		subject.releaseRunAs();
   }

}
