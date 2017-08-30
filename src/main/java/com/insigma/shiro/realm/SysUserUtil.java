package com.insigma.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Element;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.insigma.common.util.EhCacheUtil;
import com.insigma.mvc.model.CodeValue;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SUser;

/**
 * 系统工具类
 * @author wengsh
 *
 */
public class SysUserUtil {
	
	public static final String SHIRO_CURRENT_USER_INFO="SHIRO_CURRENT_USER_INFO";
	public static final String SHIRO_CURRENT_PERM_LIST_INFO="SHIRO_CURRENT_PERM_LIST_INFO";
	
	private static ThreadLocal<SUser> local = new ThreadLocal<SUser>();  
	
	  
    public static void setCurrentUser(SUser suser) {  
        local.set(suser);  
    }  
  
    public static SUser getCurrentUser() {  
        return local.get();  
    }  
  
  
    public static List<SPermission> getCurrentUserPermsList(String username) {  
       Element element=EhCacheUtil.getManager().getCache("webcache").get(SysUserUtil.SHIRO_CURRENT_PERM_LIST_INFO+"_"+username);
       if (element != null) {
			List<SPermission> list = (List<SPermission>) element.getValue();
			return list;
       }else{
    	   return null;
       }
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
   
	/**
    * 过滤三级菜单
    * @param sortlist
    */
   public static List<SPermission> filterPersmList(List< SPermission > permlist){
       List<SPermission> resultlist=new ArrayList<SPermission>();
       List<SPermission> firstTempPermlist=new ArrayList<SPermission>();
       
       //过滤掉按钮结点
       for(int i=0;i<permlist.size();i++) {
           if(permlist.get(i).getType().equals("3")){
           	    permlist.remove(i);
           	    i--;
           }
       }
       
       //先将第一级结点过滤出来
       for(int i=0;i<permlist.size();i++) {
           //如果是第一级
           if(permlist.get(i).getParentid().equals("0")){
             	firstTempPermlist.add(permlist.get(i));
           	    permlist.remove(i);
           	    i--;
           }
       }

       //再根据第一级节点过滤出第二级或三级结点
       for(int i=0;i<firstTempPermlist.size();i++){
       	   SPermission firstTempPerm=firstTempPermlist.get(i);
           List<SPermission> secondPersmList=new ArrayList<SPermission>();
           for(int j=0;j<permlist.size();j++) {
           	SPermission secondTempPerm=permlist.get(j);
               //第二级
               if(secondTempPerm.getParentid().equals(firstTempPerm.getPermissionid())){
                  List<SPermission> thirdPermList=new ArrayList<SPermission>();
                  for(int k=0;k<permlist.size();k++){
                   //第三级
                   if(permlist.get(k).getParentid().equals(secondTempPerm.getPermissionid())){
                   		  thirdPermList.add(permlist.get(k));
                       }
                   }
                   secondTempPerm.setChild(thirdPermList);
                   secondPersmList.add(secondTempPerm);
                   permlist.remove(j);
                   j--;
               }
           }
           if(secondPersmList.size()>0){
           	   firstTempPerm.setChild(secondPersmList);
           }
           resultlist.add(firstTempPerm);
       }
       return resultlist;
   }

}
