package com.goribun.navie.client;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.goribun.navie.core.annotation.RpcMapping;

/**
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class ServiceInfo {

    private String appName;
    private Class<?> clazz;// 接口类

    private String serviceName;// service名称

    private Map<String, String[]> methodParamMap;// 映射关系: 方法名=>参数名列表

    private Map<Method, String> methodUriMap = new HashMap<Method, String>();// 映射关系：方法=>接口名
    private Map<Method, RpcMapping> methodAnnotationMap = new HashMap<Method, RpcMapping>();// 映射关系：方法=>注解
    private Map<Method, Class<?>[]> methodReturnClassMap = new HashMap<Method, Class<?>[]>();// 映射关系：方法=>返回值类型

}
