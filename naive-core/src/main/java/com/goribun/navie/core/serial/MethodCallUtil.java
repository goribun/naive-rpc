package com.goribun.navie.core.serial;

import com.alibaba.fastjson.JSONObject;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-下午4:08
 * @description
 */
public class MethodCallUtil {
    public static MethodCallEntity getMethodCallEntity(String args) {
        return JSONObject.parseObject(args, MethodCallEntity.class);
    }

    public static String getMethodCallStr(MethodCallEntity entity) {
        return JSONObject.toJSONString(entity);
    }
}
