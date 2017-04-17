package com.goribun.navie.manage.regist;

import java.util.concurrent.CountDownLatch;

import com.goribun.navie.core.constants.SysErCode;
import com.goribun.navie.core.exception.SysException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-13-下午8:22
 * @description
 */
public class ZkClient implements IZkClient, Watcher {
    private static final int TIME_OUT = 3000;
    private static final String HOST = "127.0.0.1:4181,127.0.0.1:4182,127.0.0.1:4183";
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    protected ZooKeeper zooKeeper;

    @Override
    public void connection() {
        try {
            zooKeeper = new ZooKeeper(HOST, TIME_OUT, this);
            countDownLatch.await();
        } catch (Exception e) {
            throw (SysException) new SysException(SysErCode.ZK_ERR0R).initCause(e);
        }
    }

    @Override
    public void destroy() {
        if (null != zooKeeper) {
            try {
                zooKeeper.close();
            } catch (Exception e) {
                throw (SysException) new SysException(SysErCode.ZK_ERR0R).initCause(e);
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
        System.out.println("已经触发了" + event.getType() + "事件！");
    }

    @Override
    public ZooKeeper getZkConnect() {
        return zooKeeper;
    }
}
