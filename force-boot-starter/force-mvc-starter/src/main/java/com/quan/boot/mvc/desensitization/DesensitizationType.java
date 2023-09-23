package com.quan.boot.mvc.desensitization;

/**
 * @author Force-oneself
 * @date 2023-09-23
 */
public interface DesensitizationType {

    /**
     * 用户id
     */
    String USER_ID = "USER_ID";

    /**
     * 中文名
     */
    String CHINESE_NAME = "CHINESE_NAME";

    /**
     * 身份证号
     */
    String ID_CARD = "ID_CARD";

    /**
     * 座机号
     */
    String FIXED_PHONE = "FIXED_PHONE";

    /**
     * 手机号
     */
    String MOBILE_PHONE = "MOBILE_PHONE";

    /**
     * 地址
     */
    String ADDRESS = "ADDRESS";

    /**
     * 电子邮件
     */
    String EMAIL = "EMAIL";

    /**
     * 密码
     */
    String PASSWORD = "PASSWORD";

    /**
     * 中国大陆车牌，包含普通车辆、新能源车辆
     */
    String CAR_LICENSE = "CAR_LICENSE";

    /**
     * 银行卡
     */
    String BANK_CARD = "BANK_CARD";
}
