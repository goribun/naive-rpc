package com.goribun.navie.core.protocol;

/**
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class Protocol<T> {
    //编码
    private String code;
    //消息
    private String message;
    //数据
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
