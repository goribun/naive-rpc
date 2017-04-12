package com.goribun.navie.core.serial;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSONObject;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-上午9:21
 * @description
 * @see 'http://highreactor.com/article/rpc01'
 */
public class MethodCallEntity {
    private String returnType;
    private LinkedHashMap<String, Object> argList = new LinkedHashMap<String, Object>();

    public MethodCallEntity(String returnType, Object[] args) {
        this.returnType = returnType;
        if (args != null && args.length != 0) {
            for (Object object : args) {
                argList.put(object.getClass().getName(), object);
            }
        }
    }

    public static MethodCallEntity getMethodCall(String args) {
        return JSONObject.parseObject(args, MethodCallEntity.class);
    }

    public String getReturnType() {
        return returnType;
    }

    public LinkedHashMap<String, Object> getArgList() {
        return argList;
    }
}
