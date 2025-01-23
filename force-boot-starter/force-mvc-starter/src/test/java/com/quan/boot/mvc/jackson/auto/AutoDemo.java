package com.quan.boot.mvc.jackson.auto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AutoDemo {


    public static void main(String[] args) throws JsonProcessingException {
        AutoFillUser user = new AutoFillUser();
        user.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(user));
    }
}
