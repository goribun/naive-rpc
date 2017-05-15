package com.goribun.navie.core.protocol;

import java.io.Serializable;

/**
 * @author wangxuesong
 */
public class Protocol<T> implements Serializable {

    private static final long serialVersionUID = -7464366914176464832L;

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
