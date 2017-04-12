package com.goribun.navie.core.exception;

/**
 * 系统级异常
 *
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class SysException extends RuntimeException {

    private int errorCode;

    public SysException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public SysException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }
}
