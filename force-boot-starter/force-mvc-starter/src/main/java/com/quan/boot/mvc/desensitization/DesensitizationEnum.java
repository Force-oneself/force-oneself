package com.quan.boot.mvc.desensitization;

import org.springframework.util.StringUtils;

/**
 * DataMaskingFunc
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public enum DesensitizationEnum implements Operation {

    /**
     * 脱敏转换器
     */
    NO_MASK((str, maskChar) -> str),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : "*");
            }
            return sb.toString();
        }
        return str;
    });

    private final Operation operation;

    DesensitizationEnum(Operation operation) {
        this.operation = operation;
    }

    public Operation operation() {
        return this.operation;
    }

    @Override
    public String mask(String content, String maskChar) {
        return this.operation.mask(content, maskChar);
    }
}
