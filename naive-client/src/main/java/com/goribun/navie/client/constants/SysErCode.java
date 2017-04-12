package com.goribun.navie.client.constants;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-11-下午6:23
 * @description
 */
public enum SysErCode {
    IO_ERROR(0001, "IO异常");
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
