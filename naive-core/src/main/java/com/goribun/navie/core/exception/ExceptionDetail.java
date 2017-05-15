package com.goribun.navie.core.exception;

import java.util.HashMap;

/**
 * 错误信息详情
 *
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class ExceptionDetail {

    String name;
    String msg;
    ExceptionDetail cause;
    String stack;
    HashMap<String, ExceptionDetail> children;

}
