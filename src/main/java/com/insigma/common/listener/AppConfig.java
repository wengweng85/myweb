package com.insigma.common.listener;

import com.insigma.config.CustomizedPropertyConfigurer;
import com.insigma.resolver.AppException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014-12-25.
 */
public class AppConfig {


    /**
     * 通过spring获取属性值
     * @param proname
     * @return
     */
    public static String getProperties(String proname){
        return CustomizedPropertyConfigurer.getContextProperty(proname);
    }
}
