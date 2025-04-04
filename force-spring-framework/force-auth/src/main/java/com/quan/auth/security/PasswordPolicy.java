package com.quan.auth.security;

/**
 * PasswordPolicy 接口定义了密码相关的操作规范，
 * 包含密码强度验证、密码加密以及密码匹配验证等功能。
 * 实现该接口的类需要根据具体的安全策略实现这些方法。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface PasswordPolicy {
    /**
     * 验证密码强度
     * 此方法用于检查传入的明文密码是否符合预设的密码强度策略。
     * 密码强度策略可以包括密码长度、字符类型（如大写字母、小写字母、数字、特殊字符）等要求。
     * 如果密码不符合策略要求，将抛出 PasswordPolicyException 异常。
     *
     * @param password 明文密码，待验证的用户输入的密码
     * @throws PasswordPolicyException 当密码不符合预设的密码强度策略时抛出该异常
     */
    void validate(String password) throws PasswordPolicyException;

    /**
     * 密码加密
     * 该方法将传入的明文密码进行加密处理，以提高密码的安全性。
     * 加密算法可以是常见的哈希算法（如 BCrypt、SHA 等），具体实现取决于接口的实现类。
     *
     * @param rawPassword 明文密码，用户输入的原始未加密密码
     * @return 加密后的密码，经过加密算法处理后的密码字符串
     */
    String encrypt(String rawPassword);

    /**
     * 密码匹配验证
     * 此方法用于验证传入的明文密码与加密后的密码是否匹配。
     * 通常在用户登录时，将用户输入的明文密码与数据库中存储的加密密码进行比对。
     *
     * @param rawPassword 明文密码，用户输入的原始未加密密码
     * @param encodedPassword 加密后的密码，数据库中存储的经过加密处理的密码
     * @return 是否匹配，若明文密码经过加密后与加密后的密码一致则返回 true，否则返回 false
     */
    boolean matches(String rawPassword, String encodedPassword);
}
