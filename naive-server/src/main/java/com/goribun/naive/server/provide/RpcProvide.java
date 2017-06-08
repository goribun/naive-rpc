package com.goribun.naive.server.provide;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.alibaba.fastjson.JSON;
import com.goribun.naive.core.serial.MethodCallEntity;
import com.goribun.naive.core.serial.MethodCallUtil;
import com.goribun.naive.core.serial.ParameterEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @author chenchuan
 */
public class RpcProvide {

    @SuppressWarnings("unchecked")
    public static Object rpcProvide(Object obj, String methodName, String args, Class clazz) throws
            ClassNotFoundException, NoSuchMethodException {
        MethodCallEntity methodCall = MethodCallUtil.getMethodCallEntity(args);
        LinkedList<ParameterEntity> argList = methodCall.getArgList();

        Class[] parameterTypes;
        Method refMethod;

        //无参
        if (CollectionUtils.isEmpty(argList)) {
            parameterTypes = new Class[0];
        } else {
            parameterTypes = new Class[argList.size()];
            for (int i = 0; i < argList.size(); i++) {
                ParameterEntity parameterEntity = argList.get(i);
                Class parameterClass = Class.forName(parameterEntity.getClazz());
                parameterTypes[i] = parameterClass;
            }
        }

        //反射得到方法，此处修改为通过原始class获取，是因为spring代理对象反射获取会丢掉参数泛型
        refMethod = clazz.getDeclaredMethod(methodName, parameterTypes);
        //参数数组
        Object[] refArgs = getParamArray(refMethod, argList);

        if ("void".equals(methodCall.getReturnType())) {
            ReflectionUtils.invokeMethod(refMethod, obj, refArgs);
            return "void";
        } else {
            return ReflectionUtils.invokeMethod(refMethod, obj, refArgs);
        }
    }

    //取得实际参数数组
    private static Object[] getParamArray(Method refMethod, LinkedList<ParameterEntity> argList) throws
            ClassNotFoundException {
        if (CollectionUtils.isEmpty(argList)) {
            return new Object[0];
        }

        //取得泛型参数类型
        Type[] types = refMethod.getGenericParameterTypes();
        Object[] refArgs = new Object[argList.size()];

        for (int i = 0; i < argList.size(); i++) {
            ParameterEntity parameterEntity = argList.get(i);
            //JSONObject类型的解析为实际参数
            if (MethodCallUtil.isJSONObject(parameterEntity.getValue())) {
                refArgs[i] = JSON.parseObject(parameterEntity.getValue().toString(), types[i]);
            } else {
                //直接设置为参数
                refArgs[i] = parameterEntity.getValue();
            }
        }
        return refArgs;
    }
}
