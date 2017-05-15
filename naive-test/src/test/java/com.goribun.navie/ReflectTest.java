package com.goribun.navie;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.goribum.naive.services.UserServiceImpl;
import com.goribun.navie.facade.dto.UserDTO;
import com.goribun.navie.facade.intefaces.IUserService;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-下午5:20
 * @description
 */
public class ReflectTest {
    @Ignore
    @Test
    public void test_reflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        IUserService service = new UserServiceImpl();
        Class clazz = service.getClass();
        Method method = clazz.getDeclaredMethod("getUsers", null);
        System.out.println(method.toString());
        System.out.print(method.invoke(clazz.newInstance()));
    }

    @Ignore
    @Test
    public void test_reflect_void() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        IUserService service = new UserServiceImpl();
        Class clazz = service.getClass();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(99);
        userDTO.setName("999");
        Method method = clazz.getDeclaredMethod("addUser", UserDTO.class);
        System.out.println(method.toString());
        System.out.print(method.invoke(clazz.newInstance(), userDTO));
    }
}
