package com.goribun.naive.core.protocol;

import java.io.Serializable;

import com.goribun.naive.core.exception.ExceptionDetail;

/**
 * @author wangxuesong
 */
public class Protocol<T> implements Serializable {

    private static final long serialVersionUID = -7464366914176464832L;

    //编码
    private int code;
    //消息
    private String message;
    //异常信息
    private ExceptionDetail exceptionDetail;
    //数据
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public ExceptionDetail getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(ExceptionDetail exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
}
