package com.insigma.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.StringUtil;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.login.LoginService;
import com.insigma.shiro.cache.RedisCache;
import com.insigma.shiro.token.CustomUsernamePasswordToken;

@Service
@Transactional
public class MyShiroRealm extends AuthorizingRealm  implements Realm, InitializingBean {
	

	@Autowired
	private LoginService loginservice;
	
	@Autowired
    private RedisCache<String, Set<String>> redisCache;
	

	 /**
     * 认证
     */
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authcToken;
		SUser suser = loginservice.getUser(token.getUsername());
		if (suser == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if (suser.getEnabled().equals("0") ) {
            throw new LockedAccountException(); //帐号锁定
        }
	    
    	SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		suser.getUsername(), 
		suser.getPassword(), 
        getName() ); //realm name
    	setSession("suser",suser);
    	//清理缓存
    	clearCachedAuthorizationInfo(authenticationInfo.getPrincipals());
	    return authenticationInfo;
	}
	
	
	
	/**
	 * 授权
	 */
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*String loginname = (String) principals.getPrimaryPrincipal();
		try{
			if (!StringUtil.isNotEmpty(loginname)) {
	            SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
            	Set<String>  rolesset=loginservice.findRolesStr(loginname);
	            authenticationInfo.setRoles(rolesset);
	            Set<String> permissionsset=loginservice.findPermissionStr(loginname);
 	            authenticationInfo.setStringPermissions(permissionsset);
	            return authenticationInfo;
	        }else{
	            return null;
	        }
		}catch(Exception e){
			e.printStackTrace();
		}*/
        return null;
	}
	
	/**
	 * 授权基于redis,权限信息将保存到redis服务器中
	 * @param principals
	 * @return
	 */
	public AuthorizationInfo doGetAuthorizationInfo_rediscache(PrincipalCollection principals) {
		String loginname = (String) principals.getPrimaryPrincipal();
		try{
			if (!StringUtil.isNotEmpty(loginname)) {
	            SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
            	 //roles从缓存服务器中获取
	            Set<String> rolesset = redisCache.get(Constants.getUserRolesCacheKey(loginname));
	            if(rolesset!=null){
	            	authenticationInfo.setRoles(rolesset);
	            }else{
	            	rolesset=loginservice.findRolesStr(loginname);
		            authenticationInfo.setRoles(rolesset);
		            redisCache.put(Constants.getUserRolesCacheKey(loginname), rolesset);
	            }
	            //permissions从缓存服务器中获取
	            Set<String> permissionsset = redisCache.get(Constants.getUserPermissionCacheKey(loginname));
	            if(permissionsset!=null){
	            	authenticationInfo.setStringPermissions(permissionsset);
	            }else{
	            	permissionsset=loginservice.findPermissionStr(loginname);
	 	            authenticationInfo.setStringPermissions(permissionsset);
	 	            redisCache.put(Constants.getUserPermissionCacheKey(loginname), permissionsset);
	            }
	            return authenticationInfo;
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return null;
	}
	
	
	
	
	/**
	 * 清理缓存
	 * @param principal
	 */
    public void clearCachedAuthorizationInfo(String principal) {
        System.out.println("更新用户授权信息缓存");
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        super.clearCachedAuthorizationInfo(principals);
        super.clearCache(principals);
        super.clearCachedAuthenticationInfo(principals);
    }
    
	/**
	 * 清理缓存 redis
	 * @param principal
	 */
    public void clearCachedAuthorizationInfo_rediscache(String principal) {
        System.out.println("更新用户授权信息缓存");
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        super.clearCachedAuthorizationInfo(principals);
        super.clearCache(principals);
        super.clearCachedAuthenticationInfo(principals);
        redisCache.remove(Constants.getUserPermissionCacheKey(principal));
        redisCache.remove(Constants.getUserRolesCacheKey(principal));
    }

	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject subject = SecurityUtils.getSubject();  
        if(null != subject){  
            Session session = subject.getSession();  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}  
   
}
