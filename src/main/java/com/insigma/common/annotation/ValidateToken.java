package com.insigma.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检验token注解，此注解通常用于检验注解页面，即数据保存页面
 * @author wengsh
 * @date 2015-8-17
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateToken {
}
