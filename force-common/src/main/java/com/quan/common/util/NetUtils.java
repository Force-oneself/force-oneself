package com.quan.common.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @date 2023-04-12
 */
public class NetUtils {

    private NetUtils(){}

    public static void main(String[] args) throws SocketException {
        Map<String, String> networkCardRelation = macOfNetworkCard();
        System.out.println(networkCardRelation);
    }

    private static Map<String, String> macOfNetworkCard() {
        Map<String, String> networkCardRelation = new HashMap<>(16);
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback()
                        || netInterface.isVirtual()
                        || netInterface.isPointToPoint()
                        || !netInterface.isUp()) {
                    continue;
                }
                byte[] mac = netInterface.getHardwareAddress();
                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "\n"));
                    }
                    networkCardRelation.put(netInterface.getDisplayName(), sb.toString());
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return networkCardRelation;
    }
}
