package com.goribun.naive.client.http;

import okhttp3.OkHttpClient;

/**
 * @author wangxuesong
 * @version 1.0
 */
public class OkHttpUtil {

    private static OkHttpClient CLIENT = new OkHttpClient();

    private OkHttpUtil() {
    }

    public static OkHttpClient getOkHttpClient() {
        return CLIENT;
    }

}
