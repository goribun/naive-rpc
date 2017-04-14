package com.goribun.navie.server.plugings;

import java.util.Map;

import com.google.common.base.Strings;
import com.goribun.navie.core.constants.SysErCode;
import com.goribun.navie.core.exception.SysException;
import com.goribun.navie.server.annotations.RpcServer;
import com.goribun.navie.server.rpccontext.ServerContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-下午9:05
 * @description
 */
public class ServerBeanWareListener implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> map = event.getApplicationContext().getBeansWithAnnotation(RpcServer.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object impl = entry.getValue();

            RpcServer rpcServer = impl.getClass().getAnnotation(RpcServer.class);
            Class[] interfaces = impl.getClass().getInterfaces();
            String key = Strings.isNullOrEmpty(rpcServer.value()) ? interfaces[0].getName() : rpcServer.value();

            //RPC的实现类只能实现一个接口,不能多继承接口,否则报错
            if (null == interfaces || interfaces.length > 1 || null != ServerContext.getServer(key)) {
                throw new SysException(SysErCode.INIT_SERVER_ERR0R);
            }

            ServerContext.setSpringServers(key, impl);
        }
    }
}
