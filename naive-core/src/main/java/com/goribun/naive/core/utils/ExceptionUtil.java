package com.goribun.naive.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;

import com.goribun.naive.core.constants.Const;
import com.goribun.naive.core.exception.ExceptionDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RPC异常处理工具类
 *
 * @author wangxuesong
 */
public class ExceptionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtil.class);


    private ExceptionUtil() {
    }

    /**
     * 构造异常信息（序列化）
     */
    public static ExceptionDetail formatException(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        ExceptionDetail exceptionDetail = new ExceptionDetail();
        exceptionDetail.setName(throwable.getClass().getName());
        exceptionDetail.setMsg(throwable.getMessage());
        exceptionDetail.setCause(formatException(throwable.getCause()));
        if (PropertyUtil.getBooleanProperty(Const.IS_TRACE_STACK, false)) {
            LOGGER.debug("The exception message will return trace info");
            try {
                StringWriter errors = new StringWriter();
                throwable.printStackTrace(new PrintWriter(errors));
                exceptionDetail.setStack(errors.toString());
            } catch (Exception e) {
                LOGGER.warn("Return trace info error", e);
            }
        }
        return exceptionDetail;
    }

    /**
     * 实例化异常(反序列化)
     */

    public static Throwable instanceThrowable(ExceptionDetail exceptionMessage) {
        Throwable throwable;
        try {
            Class<?> cl = Class.forName(exceptionMessage.getName());
            Class[] params = {String.class};
            Constructor constructor = cl.getConstructor(params);
            throwable = (Throwable) constructor.newInstance(exceptionMessage.getMsg());
        } catch (Exception e) {
            LOGGER.error("Instantiation exception error");
            return e;
        }

        return throwable;
    }
}
