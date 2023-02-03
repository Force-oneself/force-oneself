package com.quan.demo.controller;

import com.quan.demo.framework.desensitization.annotation.Desensitization;
import com.quan.demo.framework.desensitization.config.DesensitizationAutoConfiguration;
import com.quan.demo.jackson.BigDecimalFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * com.quan.demo.controller.User
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
public class User {


    private Long id;

    @NotBlank(message = "ddd")
    @Desensitization(ops = DesensitizationAutoConfiguration.SimpleOperation.class)
    private String username;

    @BigDecimalFormat(value = "#0.0")
    private BigDecimal price;

    @BigDecimalFormat(value = "#0.0000")
    private BigDecimal height;

    private String like;

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
