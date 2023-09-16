package com.quan.framework.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Force-oneself
 * @Description MyPasswordEncoder.java
 * @date 2021-07-26
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
