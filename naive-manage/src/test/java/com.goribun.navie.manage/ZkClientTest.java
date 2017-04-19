package com.goribun.navie.manage;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2017-04-15-下午5:22
 * @description
 * @link http://blog.csdn.net/lipeng_bigdata/article/details/50986049
 */
public class ZkClientTest {

    public static void main(String[] args) {

        ZooKeeper zk = null;
        try {

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            System.out.println("开始连接ZooKeeper...");

            // 创建与ZooKeeper服务器的连接zk
            String address = "127.0.0.1:4181,127.0.0.1:4182,127.0.0.1:4183";
            int sessionTimeout = 3000;
            zk = new ZooKeeper(address, sessionTimeout, new Watcher() {
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
//                    if (event.getType() == null || "".equals(event.getType())) {
//                        return;
//                    }
                    System.out.println("已经触发了" + event.getType() + "事件！");
                }
            });

            System.out.println("ZooKeeper连接创建成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 创建根目录节点
            // 路径为/tmp_root_path
            // 节点内容为字符串"我是根目录/tmp_root_path"
            // 创建模式为CreateMode.PERSISTENT
            System.out.println("开始创建根目录节点/tmp_root_path...");
            zk.create("/tmp_root_path", "我是根目录/tmp_root_path".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("根目录节点/tmp_root_path创建成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 创建第一个子目录节点
            // 路径为/tmp_root_path/childPath1
            // 节点内容为字符串"我是第一个子目录/tmp_root_path/childPath1"
            // 创建模式为CreateMode.PERSISTENT
            System.out.println("开始创建第一个子目录节点/tmp_root_path/childPath1...");
            zk.create("/tmp_root_path/childPath1",
                    "我是第一个子目录/tmp_root_path/childPath1".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("第一个子目录节点/tmp_root_path/childPath1创建成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 创建第二个子目录节点
            // 路径为/tmp_root_path/childPath2
            // 节点内容为字符串"我是第二个子目录/tmp_root_path/childPath2"
            // 创建模式为CreateMode.PERSISTENT
            System.out.println("开始创建第二个子目录节点/tmp_root_path/childPath2...");
            zk.create("/tmp_root_path/childPath2",
                    "我是第二个子目录/tmp_root_path/childPath2".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("第二个子目录节点/tmp_root_path/childPath2创建成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 修改第一个子目录节点/tmp_root_path/childPath1数据
            System.out.println("开始修改第一个子目录节点/tmp_root_path/childPath1数据...");
            zk.setData("/tmp_root_path/childPath1",
                    "我是修改数据后的第一个子目录/tmp_root_path/childPath1".getBytes(), -1);
            System.out.println("修改第一个子目录节点/tmp_root_path/childPath1数据成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 修改第二个子目录节点/tmp_root_path/childPath2数据
            System.out.println("开始修改第二个子目录节点/tmp_root_path/childPath2数据...");
            zk.setData("/tmp_root_path/childPath2",
                    "我是修改数据后的第二个子目录/tmp_root_path/childPath2".getBytes(), -1);
            System.out.println("修改第二个子目录节点/tmp_root_path/childPath2数据成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 获取根目录节点状态
            System.out.println("开始获取根目录节点状态...");
            System.out.println(zk.exists("/tmp_root_path", true));
            System.out.println("根目录节点状态获取成功");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 删除第一个子目录节点
            System.out.println("开始删除第一个子目录节点/tmp_root_path/childPath1...");
            zk.delete("/tmp_root_path/childPath1", -1);
            System.out.println("第一个子目录节点/tmp_root_path/childPath1删除成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 删除第二个子目录节点
            System.out.println("开始删除第二个子目录节点/tmp_root_path/childPath2...");
            zk.delete("/tmp_root_path/childPath2", -1);
            System.out.println("第二个子目录节点/tmp_root_path/childPath2删除成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 修改根目录节点数据
            System.out.println("开始修改根目录节点/tmp_root_path数据...");
            zk.setData("/tmp_root_path",
                    "我是修改数据后的根目录/tmp_root_path".getBytes(), -1);
            System.out.println("修改根目录节点/tmp_root_path数据成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 获取根目录节点状态
            System.out.println("开始获取根目录节点状态...");
            System.out.println(zk.exists("/tmp_root_path", true));
            System.out.println("根目录节点状态获取成功");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

            // 删除根目录节点
            System.out.println("开始删除根目录节点/tmp_root_path...");
            zk.delete("/tmp_root_path", -1);
            System.out.println("根目录节点/tmp_root_path删除成功！");

            Thread.currentThread().sleep(1000l);

            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (zk != null) {
                try {
                    zk.close();
                    System.out.println("释放ZooKeeper连接成功！");

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}