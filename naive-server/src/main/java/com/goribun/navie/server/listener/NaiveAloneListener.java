package com.goribun.navie.server.listener;

import java.util.List;

import com.goribun.navie.core.annotation.RpcService;
import com.goribun.navie.server.context.ServiceContext;
import com.goribun.navie.server.utils.RPCServiceScanner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 单独的listener，需要在web.xml进行配置
 * <p>
 * 加载所有@RpcService的接口信息
 *
 * @author chenchuan
 */
public class NaiveAloneListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //扫描所有服务类
        List<Class<?>> serviceClassList = RPCServiceScanner.scan();

        //缓存service的class信息
        for (Class<?> clazz : serviceClassList) {
            String value = clazz.getAnnotation(RpcService.class).value();
            String key = StringUtils.isNotBlank(value) ? value : clazz.getName();
            ServiceContext.putService(key, clazz);
        }

        //        ApplicationContext cxf = event.getApplicationContext();
        //        Map<String, Object> map = cxf.getBeansWithAnnotation(RpcService.class);

        //        for (Map.Entry<String, Object> entry : map.entrySet()) {
        //            Object implObject = entry.getValue();
        //
        //            RpcService rpcServer = implObject.getClass().getAnnotation(RpcService.class);
        //            Class[] interfaces = implObject.getClass().getInterfaces();
        //
        //            String value = rpcServer.value();
        //
        //            String key = StringUtils.isNotBlank(value) ? value : interfaces[0].getName();
        //
        //            //RPC的实现类只能实现一个接口,不能多继承接口,否则报错
        //            if (null == interfaces || interfaces.length != 1 || null != ServiceContext.getServer(key)) {
        //                throw new SysException(SysErCode.INIT_SERVER_ERR0R);
        //            }
        //
        //            //缓存服务实现对象
        //            //ServiceContext.putService(key, implObject);
        //        }

    }
}
