package com.insigma.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2014-12-20.
 */
public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String, Object> ctxPropertiesMap;

    private static  Log log= LogFactory.getLog(CustomizedPropertyConfigurer.class);
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)throws BeansException {

        super.processProperties(beanFactory, props);
        //load properties to ctxPropertiesMap
        ctxPropertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    //static method for accessing context properties
    public static String getContextProperty(String name) {
        String result="";
        try{
            result=ctxPropertiesMap.get(name).toString();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return result;

    }

    //static method for accessing context properties
    public static Map<String, Object> getContextPropertyMap() {
        return ctxPropertiesMap;
    }
}
