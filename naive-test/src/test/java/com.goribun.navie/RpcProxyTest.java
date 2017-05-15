package com.goribun.navie;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.goribum.naive.Application;
import com.goribun.navie.client.poxy.RpcProxy;
import com.goribun.navie.facade.dto.UserDTO;
import com.goribun.navie.facade.intefaces.IUserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-11-下午11:34
 * @description
 */
public class RpcProxyTest {
    //    @Ignore
    //    @Before
    //    public void before() {
    //        SpringApplication.run(Application.class);
    //    }
    //
    //    @Ignore
    //    @Test
    //    public void testRpcProxy() {
    //        IUserService userService = RpcProxy.getProxy("127.0.0.1", 8090, IUserService.class);
    //        List<UserDTO> list = userService.getUsers();
    //        System.out.println("rpc调用结果" + JSONObject.toJSONString(list));
    //    }
    //
    //    @Ignore
    //    @Test
    //    public void testRpcProxy_void() {
    //        IUserService userService = RpcProxy.getProxy("127.0.0.1", 8090, IUserService.class);
    //        UserDTO userDTO = new UserDTO();
    //        userDTO.setId(3);
    //        userDTO.setName("陈大川");
    //        userService.addUser(userDTO);
    //    }

}
