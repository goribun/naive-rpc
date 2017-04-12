package com.goribun.navie.client.poxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.alibaba.fastjson.JSON;
import com.goribun.navie.core.constants.SysErCode;
import com.goribun.navie.core.exception.SysException;
import com.goribun.navie.core.serial.MethodCallEntity;
import com.goribun.navie.core.serial.MethodCallUtil;
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
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            Class returnType = method.getReturnType();
            String url = "http://" + ip + ":" + port + "/service/" + clazz.getName() + "/" + method.getName();
            MethodCallEntity entity = new MethodCallEntity(returnType.getName(), args);
            String argsJson = MethodCallUtil.getMethodCallStr(entity);
            url += "?args=" + argsJson;
            System.out.println(url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                System.out.println(result);

                if("void".equals(returnType.getName()) && "void".equals(result)){
                    return null;
                }
                return JSON.parseObject(result, returnType);
            } else {
                throw new SysException(SysErCode.OK_HTTP_ERROR);
            }
        } catch (IOException e) {
            throw (SysException) new SysException(SysErCode.IO_ERROR).initCause(e);
        } catch (Throwable e) {
            throw (SysException) new SysException(SysErCode.RPC_ERROR).initCause(e);
        }
    }
}
