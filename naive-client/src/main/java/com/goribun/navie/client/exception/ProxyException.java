package com.goribun.navie.client.exception;

import com.goribun.navie.core.exception.SysException;

/**
 * 代理异常
 *
 * @author wangxuesong wangxuesong0302@autohome.com.cn
 * @version 1.0
 */
public class ProxyException extends SysException {

    public ProxyException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ProxyException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ProxyException(String message, Throwable cause) {
        super(message, cause);
    }
}
