package com.goribun.navie.server.rpccontext;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-下午9:21
 * @description
 */
public class ServerContext {
    private static Map<String, Object> springServers = Maps.newConcurrentMap();

    public static <T> T getServer(String className) {
        return (T) springServers.get(className);
    }

    public static void setSpringServers(String key, Object obj) {
        springServers.put(key, obj);
    }
}
