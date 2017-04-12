package com.goribun.navie.client.proxy;

import com.goribun.navie.client.poxy.RpcProxy;
import com.goribun.navie.facade.dto.UserDTO;
import com.goribun.navie.facade.intefaces.IUserService;
import org.junit.Test;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-11-下午11:34
 * @description
 */
public class RpcProxtTest {
    @Test
    public void testRpcProxy() {
        IUserService userService = RpcProxy.getProxy("127.0.0.1", 8080, IUserService.class);
        userService.getUsers();
    }

    @Test
    public void testRpcProxy_void() {
        IUserService userService = RpcProxy.getProxy("127.0.0.1", 8080, IUserService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setName("陈大川").setId(3);
        userService.addUser(userDTO);
    }

}
