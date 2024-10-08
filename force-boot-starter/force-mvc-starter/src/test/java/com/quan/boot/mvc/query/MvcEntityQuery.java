package com.quan.boot.mvc.query;

import com.quan.boot.mvc.desensitization.Desensitization;
import com.quan.boot.mvc.desensitization.DesensitizationType;
import com.quan.boot.mvc.jackson.BigDecimalFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Force-oneself
 * @date 2023-09-25
 */
@Data
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

}
