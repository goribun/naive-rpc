package com.goribun.navie.server.provide;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.goribun.navie.core.constants.SysErCode;
import com.goribun.navie.core.exception.SysException;
import com.goribun.navie.core.serial.MethodCallEntity;
import com.goribun.navie.core.serial.MethodCallUtil;
import com.goribun.navie.server.rpccontext.ServerContext;
import org.springframework.util.CollectionUtils;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-下午10:15
 * @description
 */
public class RpcProvide {

    public static Object rpcProvide(String className, String methodName, String args) {
        try {
            Object impl = ServerContext.getServer(className);
            MethodCallEntity methodCall = MethodCallUtil.getMethodCallEntity(args);
            Class implClass = impl.getClass();
            LinkedList<Map<String, Object>> argList = methodCall.getArgList();
            if (CollectionUtils.isEmpty(argList)) {
                Method method = implClass.getDeclaredMethod(methodName);
                if ("void".equals(methodCall.getReturnType())) {
                    method.invoke(implClass.newInstance());
                    return "void";
                } else {
                    return method.invoke(implClass.newInstance());
                }
            } else {
                Class[] parameterTypes = new Class[argList.size()];
                Object[] refArgs = new Object[argList.size()];
                for (int i = 0; i < argList.size(); i++) {
                    Map<String, Object> map = argList.get(i);
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        Class parameterClass = Class.forName(entry.getKey());
                        parameterTypes[i] = parameterClass;
                        refArgs[i] = JSON.parseObject(entry.getValue().toString(), parameterClass);
                    }
                }
                Method refMethod = implClass.getDeclaredMethod(methodName, parameterTypes);
                if ("void".equals(methodCall.getReturnType())) {
                    refMethod.invoke(implClass.newInstance(), refArgs);
                    return "void";
                } else {
                    return refMethod.invoke(implClass.newInstance(), refArgs);
                }
            }
        } catch (Exception e) {
            throw (SysException) new SysException(SysErCode.PROVIDE_ERR0R).initCause(e);
        }
    }
}
