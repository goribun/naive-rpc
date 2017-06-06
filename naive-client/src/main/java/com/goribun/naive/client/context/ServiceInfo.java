package com.goribun.naive.client.context;

/**
 * 服务元信息
 *
 * @author wangxuesong
 */
public class ServiceInfo {

    private String appName;//项目名
    private String url;//请求地址
    private Class<?> clazz;// 接口类

    private String serviceName;// service名称

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
