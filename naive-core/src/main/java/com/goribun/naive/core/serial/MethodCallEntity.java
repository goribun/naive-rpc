package com.goribun.naive.core.serial;

import java.lang.reflect.Method;
import java.util.LinkedList;

import com.google.common.collect.Lists;

/**
 * 调用方法bean
 *
 * @author chenchuan
 */
public class MethodCallEntity {
    private String returnType;
    private LinkedList<ParameterEntity> argList = Lists.newLinkedList();

    //无参构造
    public MethodCallEntity() {

    }

    public MethodCallEntity(Method method, Object[] args) {

        this.returnType = method.getReturnType().getName();

        Class[] classes = method.getParameterTypes();
        if (args != null && args.length != 0) {
            for (int i=0;i<args.length;i++) {
                ParameterEntity parameterEntity =new ParameterEntity(classes[i].getName(),args[i]);
                argList.add(parameterEntity);
            }
        }
    }

    public String getReturnType() {
        return returnType;
    }

    public LinkedList<ParameterEntity> getArgList() {
        return argList;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setArgList(LinkedList<ParameterEntity> argList) {
        this.argList = argList;
    }
}
