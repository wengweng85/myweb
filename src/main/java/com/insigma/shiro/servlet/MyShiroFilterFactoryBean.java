package com.insigma.shiro.servlet;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;

/**
 * MyShiroFilterFactoryBean
 * @author wengsh
 *
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean { 

	@Override
    public Class getObjectType() {
        return MySpringShiroFilter.class;
    }
 
    @Override
    protected AbstractShiroFilter createInstance() throws Exception {
 
    	org.apache.shiro.mgt.SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            String msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        }
 
        if (!(securityManager instanceof WebSecurityManager)) {
            String msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        }
 
        FilterChainManager manager = createFilterChainManager();
 
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);
 
        return new MySpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
    }
}
