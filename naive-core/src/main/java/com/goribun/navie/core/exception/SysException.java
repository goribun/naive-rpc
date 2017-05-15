package com.goribun.navie.core.exception;

import com.goribun.navie.core.constants.SysErCode;

/**
 * 系统级异常
 *
 * @author wangxuesong
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 2363088507608422727L;

    private int errorCode;

    public SysException(SysErCode sysErCode) {
        super(sysErCode.getMsg());
        this.errorCode = sysErCode.getErCode();
    }

    public SysException(SysErCode sysErCode, Throwable cause) {
        super(sysErCode.getMsg(), cause);
        this.errorCode = sysErCode.getErCode();
    }
}
