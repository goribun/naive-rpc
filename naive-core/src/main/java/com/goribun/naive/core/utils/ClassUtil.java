package com.goribun.naive.core.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.goribun.naive.core.constants.Const;

/**
 * Class工具类
 *
 * @author wangxuesong
 * @version 1.0
 */
public class ClassUtil {

    private final static Map<String, Class<?>> PRIMITIVE_CLASS_NAME_MAP = new HashMap<>(8);

    static {
        PRIMITIVE_CLASS_NAME_MAP.put("byte", byte.class);
        PRIMITIVE_CLASS_NAME_MAP.put("short", short.class);
        PRIMITIVE_CLASS_NAME_MAP.put("int", int.class);
        PRIMITIVE_CLASS_NAME_MAP.put("long", long.class);
        PRIMITIVE_CLASS_NAME_MAP.put("float", float.class);
        PRIMITIVE_CLASS_NAME_MAP.put("double", double.class);
        PRIMITIVE_CLASS_NAME_MAP.put("boolean", boolean.class);
        PRIMITIVE_CLASS_NAME_MAP.put("char", char.class);
    }

    private ClassUtil() {

    }

    /**
     * 扩展的Class.forName，支持基础类型
     *
     * @param className 类名
     * @return class对象
     * @throws ClassNotFoundException 找不到类异常
     */
    public static Class<?> forNameWithPrimitive(String className) throws ClassNotFoundException {
        Class<?> clazz = PRIMITIVE_CLASS_NAME_MAP.get(className);
        //基础类型
        if (clazz != null) {
            return clazz;
        }
        return Class.forName(className);
    }

    /**
     * 根据class和object构造对象，包括short、float、long、Date等默认反序列化转换可能存在类型问题的类型
     * <p>
     * 由于未指定fastjson的实际反序列化类型，导致例如数字默认按int类型处理，此方法将其转换为实际类型的对象
     *
     * @param clazz  类型
     * @param object 对象
     * @return 实际类型的对象
     */
    public static Object parseObject(Class clazz, Object object) {

        if (object == null) {
            return null;
        }

        if (clazz.equals(byte.class) || clazz.equals(Byte.class)) {
            return ((Integer) object).byteValue();
        }

        if (clazz.equals(short.class) || clazz.equals(Short.class)) {
            return ((Integer) object).shortValue();
        }

        if (clazz.equals(long.class) || clazz.equals(Long.class)) {
            return long.class.cast(object);
        }

        if (clazz.equals(float.class) || clazz.equals(Float.class)) {
            return ((BigDecimal) object).floatValue();
        }

        if (clazz.equals(double.class) || clazz.equals(Double.class)) {
            return ((BigDecimal) object).doubleValue();
        }

        if (clazz.equals(char.class) || clazz.equals(Character.class)) {
            return String.class.cast(object).charAt(0);
        }

        //日期类型
        if (clazz.equals(Date.class)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Const.DEFAULT_DATE_FORMAT);
            try {
                return dateFormat.parse(object.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return object;
    }
}
