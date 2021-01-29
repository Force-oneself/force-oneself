package com.quan.common.util.zk;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author Force-Oneself
 * @date 2020-04-30
 */
public class ZookeeperUtils {

    private static int sessionTimeout = 2000;

    private static String connectString = "127.0.0.1:2181";

    public static void getConnection(Watcher watcher) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
