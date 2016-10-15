package com.goribun.navie.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 映射uri注解
 *
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RpcMapping {
    //默认接口或类名或方法名
    String value() default "";
}
