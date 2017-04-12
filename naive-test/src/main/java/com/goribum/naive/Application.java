package com.goribum.naive;

import com.goribum.naive.services.UserServiceImpl;
import com.goribun.navie.core.serial.MethodCallEntity;
import com.goribun.navie.facade.intefaces.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
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
    private static ApplicationContext ctx;

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    @RequestMapping("/service/{clazz}/{method}")
    public Object service(@PathVariable("clazz") String clazz, @PathVariable("method") String method, String args) {
        System.out.println(clazz);
        System.out.println(method);
        System.out.println(args);
        Object impl = ctx.getBean("userServiceImpl");
        MethodCallEntity methodCall = MethodCallEntity.getMethodCall(args);
        Class _clazz = impl.getClass();
        IUserService userService = new UserServiceImpl();
        return userService.getUsers();
    }

    public static void main(String[] args) {
        ctx = SpringApplication.run(Application.class, args);

        String[] beanNames = ctx.getBeanNamesForAnnotation(Service.class);
        System.out.println("Service注解beanNames个数：" + beanNames.length);
        for (String bn : beanNames) {
            System.out.println(bn);
        }
    }
}
