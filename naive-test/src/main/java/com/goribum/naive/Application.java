package com.goribum.naive;

import com.goribun.navie.server.provide.RpcProvide;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-11-下午9:41
 * @description
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    @RequestMapping("/service/{className}/{methodName}")
    public Object service(@PathVariable("className") String className, @PathVariable("methodName") String methodName,
                          String args) {
        return RpcProvide.rpcProvide(className, methodName, args);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        System.out.println(applicationContext.getEnvironment().getProperty("server.port"));
    }
}
