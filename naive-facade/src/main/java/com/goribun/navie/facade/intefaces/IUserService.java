package com.goribun.navie.facade.intefaces;

import java.util.List;

import com.goribun.navie.facade.dto.UserDTO;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-11-下午11:14
 * @description
 */
public interface IUserService {
    List<UserDTO> getUsers();

    void addUser(UserDTO userDTO);
}
