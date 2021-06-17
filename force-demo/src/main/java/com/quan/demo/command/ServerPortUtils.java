package com.quan.demo.command;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-02-23
 **/
@Slf4j
public class ServerPortUtils {

    public static int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();

        int port = random.nextInt(max) % (max - min + 1) + min;
        return NetUtils.isLocalePortUsing(port) ? getAvailablePort() : port;
    }

}
