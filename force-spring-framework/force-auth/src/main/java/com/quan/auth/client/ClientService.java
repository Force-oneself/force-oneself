package com.quan.auth.client;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface ClientService {

    /**
     * 注册新客户端
     *
     * @param client 客户端信息
     * @return 客户端凭证
     */
    ClientCredentials registerClient(ClientRegistration client);

    /**
     * 验证客户端身份
     *
     * @param credentials 客户端凭证
     * @return 验证通过的客户端信息
     * @throws ClientRegistrationException 客户端验证异常
     */
    RegisteredClient validateClient(ClientCredentials credentials) throws ClientRegistrationException;
}
