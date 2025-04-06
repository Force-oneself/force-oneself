package com.quan.auth.client;

import java.util.List;

/**
 * 客户端管理服务接口，用于管理第三方客户端的身份信息。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface ClientService {

    /**
     * 添加新的客户端凭证
     *
     * @param clientCredentials 客户端凭证
     */
    void addClient(ClientCredentials clientCredentials);

    /**
     * 更新现有客户端凭证
     *
     * @param clientId 客户端的唯一标识符
     * @param clientCredentials 更新后的客户端凭证
     */
    void updateClient(String clientId, ClientCredentials clientCredentials);

    /**
     * 删除客户端凭证
     *
     * @param clientId 客户端的唯一标识符
     */
    void removeClient(String clientId);

    /**
     * 获取所有客户端凭证
     *
     * @return 客户端凭证列表
     */
    List<ClientCredentials> getAllClients();

    /**
     * 根据客户端ID获取客户端凭证
     *
     * @param clientId 客户端的唯一标识符
     * @return 客户端凭证
     */
    ClientCredentials getClientById(String clientId);
}
