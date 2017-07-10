package com.insigma.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 增加token注解,此注通常用于新建页面,在此页面加入注解后，在保存页面检验
 * @author wengsh
 * @date 2015-8-17
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddToken {
       
}
