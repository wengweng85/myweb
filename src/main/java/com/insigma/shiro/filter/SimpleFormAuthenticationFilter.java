package com.insigma.shiro.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.insigma.dto.AjaxReturnMsg;


/**
 * �Զ���authcУ����
 * @author wengsh
 *
 */
public class SimpleFormAuthenticationFilter extends FormAuthenticationFilter {

	protected boolean onAccessDenied(ServletRequest servletrequest, ServletResponse servletresponse) throws Exception {  
        HttpServletRequest request = (HttpServletRequest) servletrequest;  
        HttpServletResponse response = (HttpServletResponse) servletresponse;  
        Subject subject = getSubject(request, response);  
        subject.isAuthenticated();
        //���û�е�¼
        if (subject.isAuthenticated()) {  
        	//�����ajax����
        	if (isAjax(request)){
                response.setHeader("statuscode", "unauthorized");
                response.setHeader("redirecturl", "/index");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return true;
            } else {  
                saveRequestAndRedirectToLogin(request, response);  
                return true;
            } 
        }else{
        	//�����ajax����
        	if (isAjax(request)){
        		//δ��¼��ǿ����ת����¼ҳ��
                response.setHeader("statuscode", "session_expired");
                response.setHeader("redirecturl", "/gotologin");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return true;
            } else {  
                saveRequestAndRedirectToLogin(request, response);  
                return false;
            } 
        }
    } 
	
	/**
	 * �Ƿ���ajax����
	 * @param request
	 * @return
	 */
	public boolean isAjax( HttpServletRequest request){
	      return request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1); 
	}
}
