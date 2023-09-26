package com.quan.boot.mvc.query;

import com.quan.boot.mvc.desensitization.Desensitization;
import com.quan.boot.mvc.desensitization.DesensitizationType;
import com.quan.boot.mvc.jackson.BigDecimalFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Force-oneself
 * @date 2023-09-25
 */
public class MvcEntityQuery implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    @Desensitization(type = DesensitizationType.MOBILE_PHONE)
    private String desensitization;

    @BigDecimalFormat
    private BigDecimal defaultFormat;

    @BigDecimalFormat("#0.000")
    private BigDecimal cusFormat;

    public MvcEntityQuery() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getDesensitization() {
        return this.desensitization;
    }

    public BigDecimal getDefaultFormat() {
        return this.defaultFormat;
    }

    public BigDecimal getCusFormat() {
        return this.cusFormat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDesensitization(String desensitization) {
        this.desensitization = desensitization;
    }

    public void setDefaultFormat(BigDecimal defaultFormat) {
        this.defaultFormat = defaultFormat;
    }

    public void setCusFormat(BigDecimal cusFormat) {
        this.cusFormat = cusFormat;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MvcEntityQuery)) return false;
        final MvcEntityQuery other = (MvcEntityQuery) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$age = this.getAge();
        final Object other$age = other.getAge();
        if (this$age == null ? other$age != null : !this$age.equals(other$age)) return false;
        final Object this$desensitization = this.getDesensitization();
        final Object other$desensitization = other.getDesensitization();
        if (this$desensitization == null ? other$desensitization != null : !this$desensitization.equals(other$desensitization))
            return false;
        final Object this$defaultFormat = this.getDefaultFormat();
        final Object other$defaultFormat = other.getDefaultFormat();
        if (this$defaultFormat == null ? other$defaultFormat != null : !this$defaultFormat.equals(other$defaultFormat))
            return false;
        final Object this$cusFormat = this.getCusFormat();
        final Object other$cusFormat = other.getCusFormat();
        if (this$cusFormat == null ? other$cusFormat != null : !this$cusFormat.equals(other$cusFormat)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MvcEntityQuery;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $age = this.getAge();
        result = result * PRIME + ($age == null ? 43 : $age.hashCode());
        final Object $desensitization = this.getDesensitization();
        result = result * PRIME + ($desensitization == null ? 43 : $desensitization.hashCode());
        final Object $defaultFormat = this.getDefaultFormat();
        result = result * PRIME + ($defaultFormat == null ? 43 : $defaultFormat.hashCode());
        final Object $cusFormat = this.getCusFormat();
        result = result * PRIME + ($cusFormat == null ? 43 : $cusFormat.hashCode());
        return result;
    }

    public String toString() {
        return "MvcEntityQuery(id=" + this.getId() + ", name=" + this.getName() + ", age=" + this.getAge() + ", desensitization=" + this.getDesensitization() + ", defaultFormat=" + this.getDefaultFormat() + ", cusFormat=" + this.getCusFormat() + ")";
    }
}
