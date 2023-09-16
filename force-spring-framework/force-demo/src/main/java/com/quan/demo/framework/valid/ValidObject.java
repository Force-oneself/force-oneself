package com.quan.demo.framework.valid;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ValidObject
 *
 * @author Force-oneself
 * @date 2022-12-25
 */
public class ValidObject implements Serializable {

    @NotNull(message = "主键不能为空")
    private Long id;

    private String name;

    private Integer age;

    @Length(max = 6, message = "长度不能超过6", groups = {CRUD.Create.class})
    @Length(max = 12, message = "长度不能超过12" , groups = {CRUD.Update.class})
    private String email;

    private Date date;

    private String msg;

    @Valid
    private List<ValidObject> nested;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ValidObject> getNested() {
        return nested;
    }

    public void setNested(List<ValidObject> nested) {
        this.nested = nested;
    }
}
