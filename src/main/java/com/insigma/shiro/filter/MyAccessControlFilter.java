package com.insigma.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

public class MyAccessControlFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest servletrequest, ServletResponse servletresponse) throws Exception {
		 HttpServletRequest request = (HttpServletRequest) servletrequest;  
	     HttpServletResponse response = (HttpServletResponse) servletresponse;  
	     Subject subject = getSubject(request, response);  
	     //用户拥有admin角色但没有admin2角色
	     if(subject.hasRole("admin2")&&!subject.hasRole("admin3")){
	    	 System.out.println("用户拥有admin2角色但没有admin3角色");
	    	 //重定向到对应页面
	    	 response.sendRedirect(request.getContextPath()+"/404");
	     }
		 return true;
	}

}
