package com.goribun.navie.manage.regist;

import org.apache.zookeeper.ZooKeeper;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-17-上午12:05
 * @description
 */
public interface IZkClient {
    void connection();

    void destroy();

    ZooKeeper getZkConnect();
}
