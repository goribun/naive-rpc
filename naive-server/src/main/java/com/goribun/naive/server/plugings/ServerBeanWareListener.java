package com.goribun.naive.server.plugings;

import java.util.Map;

import com.google.common.base.Strings;
import com.goribun.naive.core.constants.SysErCode;
import com.goribun.naive.core.exception.SysException;
import com.goribun.naive.server.annotations.RpcServer;
import com.goribun.navie.manage.regist.IZkClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author chenchuan
 */
public class ServerBeanWareListener implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext cxf = event.getApplicationContext();

        Map<String, Object> map = cxf.getBeansWithAnnotation(RpcServer.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //            Object impl = entry.getValue();
            //
            //            RpcServer rpcServer = impl.getClass().getAnnotation(RpcServer.class);
            //            Class[] interfaces = impl.getClass().getInterfaces();
            //            String key = Strings.isNullOrEmpty(rpcServer.value()) ? interfaces[0].getName() : rpcServer
            // .value();
            //
            //            //RPC的实现类只能实现一个接口,不能多继承接口,否则报错
            //            if (null == interfaces || interfaces.length > 1 || null != ServiceContext.getServer(key)) {
            //                throw new SysException(SysErCode.INIT_SERVER_ERR0R);
            //            }
            //
            //            ServiceContext.putService(key, impl);
            //
            //            String isRegistServer = cxf.getEnvironment().getProperty("regist.switch");
            //            if (isRegistServer.equalsIgnoreCase("true")) {
            //                IZkClient zkClient = cxf.getBean(IZkClient.class);
            //                String projectName = cxf.getEnvironment().getProperty("project.name");
            //                new RegistServerThread(zkClient, projectName).start();
            //            }

        }
    }

    public void regitServer(IZkClient client, String projectName) {
        if (null == client || Strings.isNullOrEmpty(projectName)) {
            throw new SysException(SysErCode.PROVIDE_ERR0R);
        }
        //TODO
    }

    public class RegistServerThread extends Thread {
        private IZkClient zkClient;
        private String projectName;

        public RegistServerThread(IZkClient zkClient, String projectName) {
            this.zkClient = zkClient;
            this.projectName = projectName;
        }

        @Override
        public void run() {
            regitServer(zkClient, projectName);
        }
    }

}
