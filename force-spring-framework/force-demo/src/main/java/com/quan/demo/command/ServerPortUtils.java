package com.quan.demo.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * ServerPortUtils.java
 *
 * @author Force-oneself
 * @date 2022-05-07 23:28
 */
public class ServerPortUtils {

    private final static Logger log = LoggerFactory.getLogger(ServerPortUtils.class);

    public static int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();

        int port = random.nextInt(max) % (max - min + 1) + min;
        return NetUtils.isLocalePortUsing(port) ? getAvailablePort() : port;
    }

}
