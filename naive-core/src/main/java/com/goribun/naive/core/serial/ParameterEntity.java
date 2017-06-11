package com.goribun.naive.core.serial;

/**
 * 方法参数
 *
 * @author wangxuesong
 */
public class ParameterEntity {

    //参数类型
    private String className;

    //参数值
    private Object value;

    public ParameterEntity() {
    }

    public ParameterEntity(String className, Object value) {
        this.className = className;
        this.value = value;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
