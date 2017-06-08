package com.goribun.naive.core.exception;

import java.util.HashMap;

/**
 * 错误信息详情
 *
 * @author wangxuesong
 */
public class ExceptionDetail {

    private String name;
    private String msg;
    private ExceptionDetail cause;
    private String stack;
    private HashMap<String, ExceptionDetail> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ExceptionDetail getCause() {
        return cause;
    }

    public void setCause(ExceptionDetail cause) {
        this.cause = cause;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public HashMap<String, ExceptionDetail> getChildren() {
        return children;
    }

    public void setChildren(HashMap<String, ExceptionDetail> children) {
        this.children = children;
    }
}
