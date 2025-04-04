package com.quan.auth.client;

import lombok.Getter;
import lombok.Setter;

/**
 * 客户端凭证类，用于存储客户端的身份信息。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
@Getter
@Setter
public class ClientCredentials {

    /**
     * 客户端的唯一标识符。
     */
    private String clientId;

    /**
     * 客户端的密钥，用于验证客户端的身份。
     */
    private String clientSecret;
}

