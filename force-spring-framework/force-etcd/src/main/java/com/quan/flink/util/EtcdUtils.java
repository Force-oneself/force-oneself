package com.quan.flink.util;

import io.etcd.jetcd.Client;

/**
 * EtcdUtils
 *
 * @author Force-oneself
 */
public class EtcdUtils {

    public static final String URL = "http://10.0.0.129:2379";

    public static Client getClient() {
        return Client.builder().endpoints(URL).build();
    }
}
