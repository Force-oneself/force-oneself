package com.quan.auth.token;

import java.time.Duration;

/**
 * TokenPolicy 接口定义了令牌相关的策略，包含访问令牌和刷新令牌的有效期设置，
 * 以及是否支持令牌刷新的判断逻辑。
 * 实现该接口的类可根据不同业务场景灵活配置令牌策略。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface TokenPolicy {

    /**
     * 获取访问令牌有效期
     * 此方法用于获取访问令牌的有效时长，访问令牌用于用户在一段时间内访问受保护资源。
     * 具体时长可根据业务需求在实现类中灵活配置。
     *
     * @return 访问令牌的有效时长，使用 java.time.Duration 类型表示
     */
    Duration getAccessTokenValidity();

    /**
     * 获取刷新令牌有效期
     * 该方法用于获取刷新令牌的有效时长，刷新令牌用于在访问令牌过期后获取新的访问令牌。
     * 其有效时长通常比访问令牌长，具体时长可在实现类中根据业务需求进行配置。
     *
     * @return 刷新令牌的有效时长，使用 java.time.Duration 类型表示
     */
    Duration getRefreshTokenValidity();

    /**
     * 是否支持令牌刷新
     * 此方法用于判断当前令牌策略是否支持使用刷新令牌来获取新的访问令牌。
     * 默认返回 true，表示支持刷新，实现类可根据具体业务逻辑重写该方法。
     *
     * @return 若支持令牌刷新则返回 true，否则返回 false
     */
    default boolean supportRefresh() {
        return true;
    }
}
