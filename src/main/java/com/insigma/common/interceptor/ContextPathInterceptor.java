package com.insigma.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 重复提交token检验器
 * @author wengsh
 * @date 2015-8-17
 *
 */
public class ContextPathInterceptor extends HandlerInterceptorAdapter {

	Log log=LogFactory.getLog(ContextPathInterceptor.class);
	
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			request.setAttribute("cp", request.getContextPath());
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }
}
