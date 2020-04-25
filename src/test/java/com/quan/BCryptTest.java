package com.quan;

import com.quan.uitl.BCrypt;

/**
 * BCrypt 加密算法测试
 * @author Force-Oneself
 * @date 2020-04-25
 */
public class BCryptTest {

    public static void main(String[] args) {
        // 创建一个随机的盐
        String gensalt = BCrypt.gensalt();
        System.out.println(gensalt);

        // 将密码加盐加密
        String password = BCrypt.hashpw("123456", gensalt);
        System.out.println(password);

        // 盐值比对
        boolean checkpw = BCrypt.checkpw("123456", "$2a$10$rQIa7q0GEGHe2L8GHEiHr.");
        System.out.println(checkpw);
    }
}
