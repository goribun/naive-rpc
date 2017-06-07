package com.goribun.naive.core.serial;

/**
 * 方法参数
 *
 * @author wangxuesong
 */
public class ParameterEntity {

    //参数类型
    private String clazz;

    //参数值
    private Object value;

    public ParameterEntity() {
    }

    public ParameterEntity(String clazz, Object value) {
        this.clazz = clazz;
        this.value = value;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
