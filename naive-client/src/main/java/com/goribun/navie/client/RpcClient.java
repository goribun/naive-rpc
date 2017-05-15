package com.goribun.navie.client;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.goribun.navie.client.context.ServiceInfo;
import com.goribun.navie.client.context.ServiceManager;
import com.goribun.navie.client.poxy.RpcProxy;

/**
 * 导入服务
 *
 * @author wangxuesong
 */
public class RpcClient {

    private static final Map<Class<?>, Object> SERVICE_PROXY_CACHE = new ConcurrentHashMap<>();

    static {
        // 预加载
        ServiceManager.getInstance().load();
    }

    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> clazz) {

        Object proxy = SERVICE_PROXY_CACHE.get(clazz);

        if (proxy == null) {
            SERVICE_PROXY_CACHE.put(clazz, proxy = newProxy(clazz));
        }

        return (T) proxy;
    }

    private static Object newProxy(final Class<?> clazz) {
        ServiceInfo serviceInfo = ServiceManager.getInstance().getServiceInfo(clazz);
        if (serviceInfo == null) {
            throw new RuntimeException("getServiceInfo class=" + clazz.getName() + " fail");
        }
        String url = serviceInfo.getUrl();
        String serviceName = serviceInfo.getServiceName();
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcProxy(url, serviceName));
    }

}
