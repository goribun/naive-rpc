package com.goribun.naive.core.exception;

import java.util.HashMap;

/**
 * 错误信息详情
 *
 * @author wangxuesong
 */
public class ExceptionDetail {

    String name;
    String msg;
    ExceptionDetail cause;
    String stack;
    HashMap<String, ExceptionDetail> children;

}
