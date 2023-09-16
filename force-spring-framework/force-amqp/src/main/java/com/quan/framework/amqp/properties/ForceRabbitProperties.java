package com.quan.framework.amqp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * WinkeyRabbitProperties
 *
 * @author Force-oneself
 * @date 2022-11-22
 */
@ConfigurationProperties(prefix = ForceRabbitProperties.PREFIX)
public class ForceRabbitProperties {

    public final static String PREFIX = "force.rabbit";

}
