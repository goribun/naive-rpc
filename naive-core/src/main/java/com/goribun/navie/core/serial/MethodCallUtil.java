package com.goribun.navie.core.serial;

import com.alibaba.fastjson.JSONObject;

/**
 * 调用方法序列化与反序列化工具
 *
 * @author chenchuan@autohome.com.cn
 */
public class MethodCallUtil {
    public static MethodCallEntity getMethodCallEntity(String args) {
        return JSONObject.parseObject(args, MethodCallEntity.class);
    }

    public static String getMethodCallStr(MethodCallEntity entity) {
        return JSONObject.toJSONString(entity);
    }
}
