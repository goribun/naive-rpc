package com.goribun.naive.core.exception;

/**
 * 业务级异常
 *
 * @author wangxuesong
 * @version 1.0
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -1908829941897943887L;

    private static final String DEFAULT_EXCEPTION_CODE = "-1";
    private static final String DEFAULT_EXCEPTION_MESSAGE = "业务异常";

    private String code;
    private String message;

    public BizException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
        code = DEFAULT_EXCEPTION_CODE;
    }

    public BizException(String msg) {
        super(msg);
        code = DEFAULT_EXCEPTION_CODE;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = String.valueOf(code);
    }

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(String msg, Throwable cause) {
        super(msg, cause);
        code = DEFAULT_EXCEPTION_CODE;
    }

    public BizException(Throwable cause) {
        super(cause);
        code = DEFAULT_EXCEPTION_CODE;
        message = DEFAULT_EXCEPTION_MESSAGE;
    }

    public String message() {
        return this.message;
    }

    public String code() {
        return this.code;
    }
}
