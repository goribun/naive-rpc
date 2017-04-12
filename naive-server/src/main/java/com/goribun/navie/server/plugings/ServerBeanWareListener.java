package com.goribun.navie.server.plugings;

import java.util.Map;

import com.goribun.navie.core.constants.SysErCode;
import com.goribun.navie.core.exception.SysException;
import com.goribun.navie.server.rpccontext.ServerContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-下午9:05
 * @description
 */
public class ServerBeanWareListener implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> map = event.getApplicationContext().getBeansWithAnnotation(Service.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object impl = entry.getValue();
            Class[] interfaces = impl.getClass().getInterfaces();
            if (null == interfaces || interfaces.length > 1) {
                throw new SysException(SysErCode.INIT_SERVER_ERR0R);
            }

            String key = interfaces[0].getName();
            ServerContext.setSpringServers(key, impl);
        }
    }
}
