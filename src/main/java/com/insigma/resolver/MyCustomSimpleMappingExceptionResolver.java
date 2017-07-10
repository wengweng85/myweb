package com.insigma.resolver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.insigma.dto.AjaxReturnMsg;


public class MyCustomSimpleMappingExceptionResolver  extends  SimpleMappingExceptionResolver {
	Log log=LogFactory.getLog(MyCustomSimpleMappingExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
    	//是否是开发模式,如果不是开发调试模式的话，不打印控制台日志
        e.printStackTrace();
        // Expose ModelAndView for chosen error view.
        String viewName = determineViewName(e, request);
        if (viewName != null) {// JSP格式返回
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            	// 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, e, request);
            } else {// JSON格式返回
                try {
                    PrintWriter writer = response.getWriter();
                    AjaxReturnMsg dto = new AjaxReturnMsg();
                    dto.setSuccess(false);
                    dto.setMessage(e.getMessage());
                    writer.write(JSONObject.fromObject(dto).toString());
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        } else {
             return null;
        }
    }

}
