package com.quan.boot.mvc.jackson.auto;

public class AutoFillUser {

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
