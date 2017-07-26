package com.insigma.common.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.insigma.common.annotation.AddToken;
import com.insigma.common.annotation.ValidateToken;
import com.insigma.common.filter.CSRFTokenManager;
import com.insigma.common.util.EhCacheUtil;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.model.CodeValue;
import com.insigma.resolver.AppException;

import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

/**
 * 重复提交token检验器
 * @author wengsh
 * @date 2015-8-17
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	Log log=LogFactory.getLog(TokenInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            
            Method method = handlerMethod.getMethod();
            AddToken addtoken = method.getAnnotation(AddToken.class);
            ValidateToken validatetoken = method.getAnnotation(ValidateToken.class);
            if (addtoken != null) {
            	setToken(request, response);
            }
            if(validatetoken!=null){
            	if (validToken(request)) {
            		 //生定向到重复提交页面
            		 if(isAjax(request))
            		 {
            			 PrintWriter writer = response.getWriter();
                         AjaxReturnMsg dto = new AjaxReturnMsg();
                         Map<String, Object> map=new HashMap<String, Object>();
                         map.put("statuscode", "resubmit");//重复提交
                         dto.setObj(map);
                         writer.write(JSONObject.fromObject(dto).toString());
                         writer.flush();
            		 }
            		 else{
            			 request.getRequestDispatcher("/resubmit").forward(request, response);
            		 }
                     return false;
                }else{
                	CSRFTokenManager.removeTokenFromRequest(request,response);
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 设置令牌
     * @param request
     */
    public void setToken(HttpServletRequest request,HttpServletResponse response) throws AppException{
    	String token=TokenUtil.createToken();
        request.setAttribute("csrf", token );
        request.getSession().setAttribute("csrftoken",token);
        /*Vector<String> csrftokenlist = (Vector<String>)request.getSession().getAttribute("csrftokenlist");
        if(csrftokenlist == null){
        	csrftokenlist = new Vector<String>();
        }
        csrftokenlist.add(token);
        request.getSession().setAttribute("csrftokenlist",csrftokenlist);*/
        EhCacheUtil.getManager().getCache("tokencache").put(new Element(token,""));
        System.out.println("缓存数："+EhCacheUtil.getManager().getCache("tokencache").getSize());
    }

   

   /**
    * 检验令牌 
    * @param request
    * @return true 是重复提交 false 不是重复提交
    * @throws AppException
    */
    public boolean validToken(HttpServletRequest request) throws AppException {
    	//Vector<String> csrftokenlist = (Vector<String>)request.getSession().getAttribute("csrftokenlist");
        String clientToken=CSRFTokenManager.getTokenFromRequest(request);
        if (null==clientToken){
        	String info="当前请求为重复提交请求,原因客户端token为空:clientToken:"+clientToken;
        	log.error(info);
        	return true;
        }
        /*if (null==csrftokenlist) {
        	String info="当前请求为重复提交请求,原因服务端token为空:serverToken:''   clientToken:"+clientToken;
        	log.error(info);
        	return true;
        }*/
        Element element=EhCacheUtil.getManager().getCache("tokencache").get(clientToken);
        if(element!=null){
        	EhCacheUtil.getManager().getCache("tokencache").remove(clientToken);
            System.out.println("剩余缓存数："+EhCacheUtil.getManager().getCache("tokencache").getSize());
        	return false;
  		}
        String info="当前请求为重复提交请求,原因客户端与服务端token不一致: clientToken:"+clientToken;
    	log.error(info);
    	return true;
    	
        /*for (int i = 0; i < csrftokenlist.size(); i++) {
			if (csrftokenlist.get(i).toString().equals(clientToken)){
				csrftokenlist.remove(i);
		        request.getSession().setAttribute("csrftokenList",csrftokenlist);
				return false;
			}
		}
        String info="当前请求为重复提交请求,原因客户端与服务端token不一致: clientToken:"+clientToken;
    	log.error(info);
    	return true;*/
        
    	
        /*String serverToken=(String)request.getSession().getAttribute("csrftoken");
        String clientToken=CSRFTokenManager.getTokenFromRequest(request);
        if (null==clientToken){
        	String info="当前请求为重复提交请求,原因客户端token为空:serverToken:"+serverToken+"  clientToken:"+clientToken;
        	log.error(info);
        	return true;
        }
        if (null==serverToken) {
        	String info="当前请求为重复提交请求,原因服务端token为空:serverToken:"+serverToken+"   clientToken:"+clientToken;
        	log.error(info);
        	return true;
        }
        if (!serverToken.equals(clientToken)) {
        	String info="当前请求为重复提交请求,原因客户端与服务端token不一致:serverToken:"+serverToken+"   clientToken:"+clientToken;
        	log.error(info);
        	return true;
        }
       
        return false;*/
    }
    
    /**
	 * 是否是ajax请求
	 * @param request
	 * @return
	 */
	public boolean isAjax( HttpServletRequest request){
	      return request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1); 
	}
}
