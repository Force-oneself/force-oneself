package com.quan.demo.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

/**
 * ObjectMapperDemo
 *
 * @author Force-oneself
 * @date 2022-09-23
 */
public class ObjectMapperDemo {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(new Date());
    }
}
