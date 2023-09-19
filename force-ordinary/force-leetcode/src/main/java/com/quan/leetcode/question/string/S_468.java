package com.quan.leetcode.question.string;

/**
 * S_468
 *
 * @author Force-oneself
 * @date 2022-05-26
 */
public class S_468 {

    public String validIPAddress(String queryIP) {
        return queryIP.indexOf(".") > 0 ? ip4(queryIP) : ip6(queryIP);
    }

    private String ip6(String queryIP) {
        if (queryIP.endsWith(":")) {
            return "Neither";
        }
        String[] ip6 = queryIP.split(":");
        if (ip6.length != 8) {
            return "Neither";
        }
        for (String p : ip6) {
            int pLen = p.length();
            if (pLen > 4 || pLen < 1 || !isOx(p)) {
                return "Neither";
            }
        }
        return "IPv6";
    }

    private boolean isOx(String p) {
        int len = p.length();
        for (int i = 0; i < len; i++) {
            char c = p.charAt(i);
            if (Character.isDigit(c)) {
                continue;
            }
            if (Character.isLetter(c)) {
                if (!(('a' <= c && 'f' >= c) || ('A' <= c && 'F' >= c))) {
                    return false;
                }
            }
        }
        return true;
    }

    private String ip4(String queryIP) {
        if (queryIP.endsWith(".")) {
            return "Neither";
        }
        String[] ip4 = queryIP.split("\\.");
        if (ip4.length != 4) {
            return "Neither";
        }
        for (String p : ip4) {
            int pLen = p.length();
            if (pLen > 3 || pLen < 1) {
                return "Neither";
            }
            int num = 0;
            for (int i = 0; i < pLen; i++) {
                int n = p.charAt(i) - '0';
                if (i == 0 && n == 0 && pLen != 1) {
                    return "Neither";
                }
                if (!Character.isDigit(p.charAt(i))) {
                    return "Neither";
                }
                num = num * 10 + n;
            }
            if (num > 255 || num < 0) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    public static void main(String[] args) {
//        System.out.println(new S_468().validIPAddress("172.16.254.1"));
//        System.out.println(new S_468().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(new S_468().validIPAddress(":2001:0db8:85a3:0:0:8A2E:0370:7334:"));
//        System.out.println(new S_468().validIPAddress("256.256.256.256"));
    }
}
