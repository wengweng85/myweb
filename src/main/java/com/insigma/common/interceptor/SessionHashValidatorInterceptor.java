package com.insigma.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.insigma.common.util.MD5Util;
import com.insigma.mvc.service.login.LoginService;

/**
 * 登录hashcode相关信息校验
 * @author wengsh
 * @date 2015-8-17
 *
 */
public class SessionHashValidatorInterceptor extends HandlerInterceptorAdapter {

	Log log=LogFactory.getLog(SessionHashValidatorInterceptor.class);

	@Autowired
	private LoginService loginservice;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			Subject subject = SecurityUtils.getSubject();  
			if(subject.isAuthenticated()){
				if(loginservice.findLoginInfoByhashcode(getReqeustHashcode(request))!=null){
					return true;
				}else{
					return false;
				}
			}
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }
    
	/**
	 * 获取ip+usergent+sessionid的hashcode
	 * @param request
	 * @return
	 */
	public String getReqeustHashcode(HttpServletRequest request ){
		String ip=request.getRemoteHost();
		String useragent=request.getHeader("User-Agent");
		String sessionid=request.getSession().getId();
		return MD5Util.MD5Encode(ip+useragent+sessionid);
	}
}
