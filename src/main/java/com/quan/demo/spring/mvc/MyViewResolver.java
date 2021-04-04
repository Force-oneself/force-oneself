package com.quan.demo.spring.mvc;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-04-04
 **/
public class MyViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return null;
    }
}
