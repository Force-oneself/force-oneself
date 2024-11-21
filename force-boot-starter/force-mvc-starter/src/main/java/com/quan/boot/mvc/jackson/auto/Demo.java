package com.quan.boot.mvc.jackson.auto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Demo {


    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(user));
    }

    public static class User {
        private String name;
        @AutoFill("default")
        //@JsonSerialize(using = AutoFillSerializer.class)
        private Long id;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
    }
}
