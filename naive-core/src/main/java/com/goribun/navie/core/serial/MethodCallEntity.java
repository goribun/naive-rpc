package com.goribun.navie.core.serial;

import java.util.LinkedList;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-上午9:21
 * @description
 * @see 'http://highreactor.com/article/rpc01'
 */
public class MethodCallEntity {
    private String returnType;
    private LinkedList<Map<String, Object>> argList = Lists.newLinkedList();

    public MethodCallEntity() {
        //empty constructor
    }

    public MethodCallEntity(String returnType, Object[] args) {
        this.returnType = returnType;
        if (args != null && args.length != 0) {
            for (Object object : args) {
                Map<String, Object> map = Maps.newHashMap();
                map.put(object.getClass().getName(), object);
                argList.add(map);
            }
        }
    }

    public String getReturnType() {
        return returnType;
    }

    public LinkedList<Map<String, Object>> getArgList() {
        return argList;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setArgList(LinkedList<Map<String, Object>> argList) {
        this.argList = argList;
    }
}
