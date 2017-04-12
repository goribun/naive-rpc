package com.goribun.navie.client.poxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goribun.navie.client.constants.SysErCode;
import com.goribun.navie.client.exception.ProxyException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class RpcProxy implements InvocationHandler {
    private OkHttpClient client = new OkHttpClient();

    private Class<?> clazz;
    private String ip;
    private int port;

    public RpcProxy(String ip, int port, Class<?> clazz) {
        this.ip = ip;
        this.port = port;
        this.clazz = clazz;
    }

    public static <T> T getProxy(String ip, int port, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new RpcProxy(ip, port, clazz));
    }

    /**
     * 拼接http请求,进行get请求
     * 列入:http://127.0.0.1:8080/service/类名/方法名?args={key1:value1,key2:value2}
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws ProxyException {
        try {
            String url = "http://" + ip + ":" + port + "/service/" + clazz.getName() + "/" + method.getName();
            String argsJson = JSON.toJSONString(args);
            url += "?args=" + argsJson;
            System.out.println(url);
            Request requert = new Request.Builder().url(url).build();
            Response response = client.newCall(requert).execute();
            Class<?> returnType = method.getReturnType();
            if (response.isSuccessful()) {
                String result = response.body().string();
                return JSON.parseObject(result,returnType.getClass());
            } else {
                throw new ProxyException(SysErCode.IO_ERROR.getErCode(), SysErCode.IO_ERROR.getMsg());
            }
        } catch (Throwable t) {
            throw new ProxyException(SysErCode.IO_ERROR.getErCode(), SysErCode.IO_ERROR.getMsg(), t.getCause());
        }
    }
}
