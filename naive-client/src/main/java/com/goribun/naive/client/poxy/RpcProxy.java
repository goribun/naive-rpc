package com.goribun.naive.client.poxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;
import com.goribun.naive.core.constants.SysErCode;
import com.goribun.naive.core.exception.SysException;
import com.goribun.naive.core.protocol.Protocol;
import com.goribun.naive.core.serial.MethodCallEntity;
import com.goribun.naive.core.serial.MethodCallUtil;
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
    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IOException {

        String methodName = method.getName();
        if ("toString".equals(methodName)) {
            return String.format("host:%s->service:%s", host, serviceName);
        }
        if ("hashCode".equals(methodName)) {
            return null;
        }

        String url = host + "/service/" + serviceName + "/" + method.getName();
        MethodCallEntity entity = new MethodCallEntity(method, args);
        String argsJson = MethodCallUtil.getMethodCallStr(entity);
        url += "?args=" + argsJson;

        Class returnType = method.getReturnType();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String resultString = response.body().string();
            //接收到的协议体
            Protocol protocol = JSON.parseObject(resultString, Protocol.class);

            if ("void".equals(returnType.getName()) && "void".equals(resultString)) {
                return null;
            }
            //返回反序列化的值
            return JSON.parseObject(JSON.toJSONString(protocol.getData()), returnType);
        } else {
            throw new SysException(SysErCode.OK_HTTP_ERROR);
        }
    }

}
