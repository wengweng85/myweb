package com.insigma.mvc.component.appcontext;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * MyApplicationContextUtil
 * @author wengsh
 *
 */
@Component
public class MyApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;// 声明一个静态变量保存

	public void setApplicationContext(ApplicationContext contex)throws BeansException {
		context = contex;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	
	public static Object getBean(ServletContext servletContext,Class beanname){
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        Object bean =  wac.getBean(beanname);
        return bean;
    }
}
