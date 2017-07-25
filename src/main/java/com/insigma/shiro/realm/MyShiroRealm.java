package com.insigma.shiro.realm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
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
     * ��֤
     */
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authcToken;
		SUser suser = loginservice.getUser(token.getUsername());
		if (suser == null) {
            throw new UnknownAccountException();//û�ҵ��ʺ�
        }

        if (suser.getEnabled().equals("0") ) {
            throw new LockedAccountException(); //�ʺ�����
        }
	    
    	SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		suser.getUsername(), 
		suser.getPassword(), 
        getName() ); //realm name
    	setSession("suser",suser);
    	//������
    	clearCachedAuthorizationInfo(authenticationInfo.getPrincipals());
	    return authenticationInfo;
	}
	
	
	
	/**
	 * ��Ȩ
	 */
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginname = (String) principals.getPrimaryPrincipal();
		try{
			if (StringUtil.isNotEmpty(loginname)) {
	            SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
 	           //�û���ɫ
 	            List<SRole> rolelist=loginservice.findRolesStr(loginname);
 	            Set<String> roleset=new HashSet<String>();
 	            Iterator iterator_role=rolelist.iterator();
 	            while(iterator_role.hasNext()){
 	            	SRole  srole=(SRole) iterator_role.next();
 	            	if(srole.getCode()!=null){
 	            		roleset.add(srole.getCode());
	            	}
	            }
 	           authenticationInfo.setRoles(roleset);
 	           
 	            //�û�Ȩ��
	            List<SPermission> permlist=loginservice.findPermissionStr(loginname);
	            Set<String> set=new HashSet<String>();
	            Iterator iterator=permlist.iterator();
	            while(iterator.hasNext()){
	            	SPermission  spermission=(SPermission) iterator.next();
	            	if(spermission.getCode()!=null){
	            		set.add(spermission.getCode());
	            	}
	            }
 	            authenticationInfo.setStringPermissions(set);
	            return authenticationInfo;
	        }else{
	            return null;
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return null;
	}
	
	/**
	 * ��Ȩ����redis,Ȩ����Ϣ�����浽redis��������
	 * @param principals
	 * @return
	 */
	public AuthorizationInfo doGetAuthorizationInfo_rediscache(PrincipalCollection principals) {
		String loginname = (String) principals.getPrimaryPrincipal();
		try{
			if (StringUtil.isNotEmpty(loginname)) {
	            SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
            	 //roles�ӻ���������л�ȡ
	            Set<String> rolesset = redisCache.get(Constants.getUserRolesCacheKey(loginname));
	            if(rolesset!=null){
	            	authenticationInfo.setRoles(rolesset);
	            }else{
	            	//�û���ɫ
	 	            List<SRole> rolelist=loginservice.findRolesStr(loginname);
	 	            Set<String> roleset=new HashSet<String>();
	 	            Iterator iterator_role=rolelist.iterator();
	 	            while(iterator_role.hasNext()){
	 	            	SRole  srole=(SRole) iterator_role.next();
	 	            	roleset.add(srole.getName());
	 	            	if(srole.getCode()!=null){
	 	            		roleset.add(srole.getCode());
		            	}
		            }
		            authenticationInfo.setRoles(roleset);
		            redisCache.put(Constants.getUserRolesCacheKey(loginname), rolesset);
	            }
	            //permissions�ӻ���������л�ȡ
	            Set<String> permissionsset = redisCache.get(Constants.getUserPermissionCacheKey(loginname));
	            if(permissionsset!=null){
	            	authenticationInfo.setStringPermissions(permissionsset);
	            }else{
	            	//�û�Ȩ��
		            List<SPermission> permlist=loginservice.findPermissionStr(loginname);
		            Set<String> set=new HashSet<String>();
		            Iterator iterator=permlist.iterator();
		            while(iterator.hasNext()){
		            	SPermission  spermission=(SPermission) iterator.next();
		            	if(spermission.getCode()!=null){
		            		set.add(spermission.getCode());
		            	}
		            }
	 	            authenticationInfo.setStringPermissions(set);
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
	 * ������
	 * @param principal
	 */
    public void clearCachedAuthorizationInfo(String principal) {
        System.out.println("�����û���Ȩ��Ϣ����");
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        super.clearCachedAuthorizationInfo(principals);
        super.clearCache(principals);
        super.clearCachedAuthenticationInfo(principals);
    }
    
	/**
	 * ������ redis
	 * @param principal
	 */
    public void clearCachedAuthorizationInfo_rediscache(String principal) {
        System.out.println("�����û���Ȩ��Ϣ����");
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        super.clearCachedAuthorizationInfo(principals);
        super.clearCache(principals);
        super.clearCachedAuthenticationInfo(principals);
        redisCache.remove(Constants.getUserPermissionCacheKey(principal));
        redisCache.remove(Constants.getUserRolesCacheKey(principal));
    }

	/** 
     * ��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ�� 
     * @see  ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ�� 
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
