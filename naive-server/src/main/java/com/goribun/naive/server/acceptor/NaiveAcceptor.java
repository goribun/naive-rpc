package com.goribun.naive.server.acceptor;

import javax.annotation.Resource;

import com.goribun.naive.core.exception.BizException;
import com.goribun.naive.core.protocol.Protocol;
import com.goribun.naive.server.context.ServiceContext;
import com.goribun.naive.server.provide.RpcProvide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求接收器
 *
 * @author wangxuesong
 */
@RestController
@RequestMapping("/service")
public class NaiveAcceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(NaiveAcceptor.class);

    @Resource
    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    @RequestMapping("/{className}/{methodName}")
    public ResponseEntity<Protocol> service(@PathVariable("className") String className, @PathVariable("methodName")
            String methodName, String args) {

        Class clazz = ServiceContext.getServer(className);
        Object implObj = applicationContext.getBean(clazz);

        Protocol protocol = new Protocol();
        HttpStatus httpStatus = HttpStatus.OK;

        Object result = null;
        try {
            result = RpcProvide.rpcProvide(implObj, methodName, args);
        } catch (BizException e) {
            //业务异常
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.error(e.getMessage(), e);
        }

        protocol.setData(result);

        ResponseEntity<Protocol> responseEntity = new ResponseEntity<>(protocol, httpStatus);

        return responseEntity;

    }

}
