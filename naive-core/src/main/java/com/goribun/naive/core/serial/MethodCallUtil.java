package com.goribun.naive.core.serial;

import com.alibaba.fastjson.JSONObject;
import com.goribun.naive.core.constants.Const;

/**
 * 调用方法序列化与反序列化工具
 *
 * @author chenchuan
 */
public class MethodCallUtil {
    public static MethodCallEntity getMethodCallEntity(String args) {
        return JSONObject.parseObject(args, MethodCallEntity.class);
    }

    public static String getMethodCallStr(MethodCallEntity entity) {
        return JSONObject.toJSONStringWithDateFormat(entity, Const.DEFAULT_DATE_FORMAT);
    }

    /**
     * 判断是否是JSONObject
     */
    public static boolean isJSONObject(Object obj) {
        return obj != null && obj instanceof JSONObject;
    }
}
