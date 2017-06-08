package com.goribun.naive.core.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * naive-rpc自定义配置读取
 *
 * @author wangxuesong
 */
public class PropertyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps() {

        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("naive-rpc.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            LOGGER.warn("naive-rpc.properties not found");
        } catch (IOException e) {
            LOGGER.error("loadProps error", e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                LOGGER.error("naive-rpc.properties close error", e);
            }
        }
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        if (null == props) {
            loadProps();
        }

        try {
            return Boolean.valueOf(props.getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
