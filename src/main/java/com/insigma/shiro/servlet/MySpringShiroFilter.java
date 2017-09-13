package com.insigma.shiro.servlet;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

/*
* ×Ô¶¨ÒåShiroFilte
*/
public class MySpringShiroFilter extends AbstractShiroFilter {
   protected MySpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
       super();
       if (webSecurityManager == null) {
           throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
       }
       setSecurityManager(webSecurityManager);
       if (resolver != null) {
           setFilterChainResolver(resolver);
       }
   }

   @Override
   protected ServletResponse wrapServletResponse(HttpServletResponse orig, ShiroHttpServletRequest request) {
       return new MyShiroHttpServletResponse(orig, getServletContext(), request);
   }
}
