package com.goribun.naive.server.provide;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.goribun.naive.core.constants.SysErCode;
import com.goribun.naive.core.exception.SysException;
import com.goribun.naive.core.serial.MethodCallEntity;
import com.goribun.naive.core.serial.MethodCallUtil;
import org.springframework.util.CollectionUtils;

/**
 * @author chenchuan
 */
public class RpcProvide {

    @SuppressWarnings("unchecked")
    public static Object rpcProvide(Object obj, String methodName, String args, Class clazz) {
        try {

            MethodCallEntity methodCall = MethodCallUtil.getMethodCallEntity(args);

            LinkedList<Map<String, Object>> argList = methodCall.getArgList();
            if (CollectionUtils.isEmpty(argList)) {
                Method method = obj.getClass().getDeclaredMethod(methodName);
                if ("void".equals(methodCall.getReturnType())) {
                    method.invoke(obj);
                    return "void";
                } else {
                    return method.invoke(obj);
                }
            } else {
                Class[] parameterTypes = new Class[argList.size()];
                Object[] refArgs = new Object[argList.size()];
                for (int i = 0; i < argList.size(); i++) {
                    Map<String, Object> map = argList.get(i);
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        Class parameterClass = Class.forName(entry.getKey());
                        parameterTypes[i] = parameterClass;
                    }
                }

                //反射得到方法，此处修改为通过原始class获取，是因为spring代理对象反射获取会丢掉参数泛型
                Method refMethod = clazz.getDeclaredMethod(methodName, parameterTypes);
                //取得泛型参数类型
                Type[] types = refMethod.getGenericParameterTypes();

                for (int i = 0; i < argList.size(); i++) {
                    Map<String, Object> map = argList.get(i);
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        refArgs[i] = JSON.parseObject(entry.getValue().toString(), types[i]);
                    }
                }

                if ("void".equals(methodCall.getReturnType())) {
                    refMethod.invoke(obj.getClass().newInstance(), refArgs);
                    return "void";
                } else {
                    return refMethod.invoke(obj, refArgs);
                }
            }
        } catch (Exception e) {
            throw (SysException) new SysException(SysErCode.PROVIDE_ERR0R).initCause(e);
        }
    }

}
