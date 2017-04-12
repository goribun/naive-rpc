package com.goribum.naive.services;

import java.util.ArrayList;
import java.util.List;

import com.goribun.navie.facade.dto.UserDTO;
import com.goribun.navie.facade.intefaces.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-12-上午12:25
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {
    private static List<UserDTO> list = new ArrayList<UserDTO>();

    static {
        UserDTO u1 = new UserDTO();
        u1.setId(1);
        u1.setName("陈川");

        UserDTO u2 = new UserDTO();
        u2.setId(2);
        u2.setName("陈小川");


        list.add(u1);
        list.add(u2);
    }

    public List<UserDTO> getUsers() {
        return list;
    }

    public void addUser(UserDTO userDTO) {
        list.add(userDTO);
    }
}
