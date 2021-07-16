package com.quan.demo.command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-02-23
 **/
public class NetUtils {

    private final static Logger log = LoggerFactory.getLogger(NetUtils.class);

    public static boolean isLocalePortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
            log.error("未知主机异常", e);
        }
        return flag;
    }

    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {
            log.error("端口已占用!", e);
        }
        return flag;
    }
}
