package com.goribun.navie.server.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端RPC服务上线文
 *
 * @author chenchuan
 */
public class ServiceContext {

    private ServiceContext() {
    }

    private static Map<String, Class<?>> serviceMap = new ConcurrentHashMap<>();

    public static Class<?> getServer(String className) {
        return serviceMap.get(className);
    }

    public static void putService(String className, Class<?> obj) {
        serviceMap.put(className, obj);
    }
}
