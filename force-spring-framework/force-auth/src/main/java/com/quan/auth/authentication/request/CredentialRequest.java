package com.quan.auth.authentication.request;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证请求接口，定义认证请求的通用能力
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface CredentialRequest {

    /**
     * 获取认证类型
     *
     * @return 认证类型标识
     */
    String getAuthenticationType();

    /**
     * 获取认证主体标识（可以是用户名、手机号、邮箱等）
     *
     * @return 认证主体标识
     */
    String getPrincipal();

    /**
     * 获取认证凭证（可以是密码、验证码、token等）
     *
     * @return 认证凭证
     */
    String getCredential();

    /**
     * 获取认证来源（如Web、App、小程序等）
     *
     * @return 认证来源
     */
    default String getSource() {
        return "unknown";
    }

    /**
     * 获取设备信息（如设备ID、设备类型等）
     *
     * @return 设备信息
     */
    Map<String, String> getDeviceInfo();

    /**
     * 获取额外认证参数
     *
     * @return 额外参数
     */
    Map<String, Object> getExtendParams();

    /**
     * 是否记住登录
     *
     * @return true表示记住登录
     */
    default boolean isRememberMe() {
        return false;
    }

    /**
     * 获取认证请求时间戳
     *
     * @return 时间戳
     */
    default long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取属性
     */
    default <T> T getAttribute(Class<T> type) {
        Map<String, Object> params = getExtendParams();
        return type.cast(params.get(type.getName()));
    }

    /**
     * 设置属性
     */
    default <T> void setAttribute(Class<T> type, T value) {
        Map<String, Object> params = getExtendParams();
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(type.getName(), value);
    }
}
