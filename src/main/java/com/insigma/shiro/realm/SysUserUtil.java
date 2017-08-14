package com.insigma.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.insigma.mvc.model.SUser;

/**
 * ϵͳ������
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
     * ���µ�ǰ�û�Ȩ��,ϵͳ�ڶ��û�����Ȩ���޸ĺ����ͨ�����ô˷�ʽ��̬�޸ĵ�ǰ�û�Ȩ��
     */
   public static void updateCurrentUserPerms(){
		Subject subject = SecurityUtils.getSubject(); 
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();  
		MyShiroRealm shiroRealm = (MyShiroRealm)rsm.getRealms().iterator().next();  
		String realmName = subject.getPrincipals().getRealmNames().iterator().next(); 
		//��һ������Ϊ�û���,�ڶ�������ΪrealmName
		SimplePrincipalCollection principals = new SimplePrincipalCollection(SysUserUtil.getCurrentUser().getUsername(),realmName); 
		subject.runAs(principals); 
		shiroRealm.getAuthorizationCache().remove(subject.getPrincipals()); 
		subject.releaseRunAs();
   }

}
