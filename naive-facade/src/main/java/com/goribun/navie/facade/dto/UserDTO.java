package com.goribun.navie.facade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-11-下午11:10
 * @description
 */
@Accessors(chain = true)
@Setter
@Getter
public class UserDTO {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
