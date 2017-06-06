package com.goribun.naive.core.constants;

/**
 * @author chenchuan
 */
public enum SysErCode {
    IO_ERROR(0001, "IO异常"),
    OK_HTTP_ERROR(0002, "okhttp请求错误"),
    RPC_ERROR(0003, "RPC调用失败"),
    INIT_SERVER_ERR0R(0004, "初始化服务端失败"),
    PROVIDE_ERR0R(0005, "提供rpc服务失败"),
    ZK_ERR0R(0006, "zookeeper服务失败");
    private int erCode;
    private String msg;

    SysErCode(int erCode, String msg) {
        this.erCode = erCode;
        this.msg = msg;
    }

    public int getErCode() {
        return erCode;
    }

    public String getMsg() {
        return msg;
    }

    public SysErCode getSysErCode(int erCode) {
        for (SysErCode sys : SysErCode.values()) {
            if (sys.getErCode() == erCode) {
                return sys;
            }
        }
        return null;
    }
}
