package com.goribun.navie.client.poxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;
import com.goribun.navie.core.constants.SysErCode;
import com.goribun.navie.core.exception.SysException;
import com.goribun.navie.core.serial.MethodCallEntity;
import com.goribun.navie.core.serial.MethodCallUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Rpc代理
 *
 * @author wangxuesong
 */
public class RpcProxy implements InvocationHandler {

    private OkHttpClient client = new OkHttpClient();

    //private Class<?> clazz;
    private String serviceName;
    private String host;

    public RpcProxy(String host, String serviceName) {
        this.host = host;
        this.serviceName = serviceName;
    }

    /**
     * 发送请求
     * 列入:http://127.0.0.1:8080/service/类名/方法名?args={key1:value1,key2:value2}
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            Class returnType = method.getReturnType();


            String url = host + "/service/" + serviceName + "/" + method.getName();

            System.out.println("===========" + url);

            MethodCallEntity entity = new MethodCallEntity(returnType.getName(), args);
            String argsJson = MethodCallUtil.getMethodCallStr(entity);
            url += "?args=" + argsJson;
            System.out.println(url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                System.out.println(result);

                if ("void".equals(returnType.getName()) && "void".equals(result)) {
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
