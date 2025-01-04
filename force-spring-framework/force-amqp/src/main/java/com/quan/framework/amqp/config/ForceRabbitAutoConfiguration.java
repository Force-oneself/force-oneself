package com.quan.framework.amqp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.quan.framework.amqp.advice.DefaultRabbitManualAcknowledgeFactory;
import com.quan.framework.amqp.advice.RabbitAdviceFactory;
import com.quan.framework.amqp.callback.RabbitCallback;
import com.quan.framework.amqp.properties.ForceRabbitProperties;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitAutoConfiguration
 *
 * @author Force-oneself
 * @date 2022-11-21
 */
@ConditionalOnClass(RabbitTemplate.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ForceRabbitProperties.class)
public class ForceRabbitAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RabbitCallback rabbitMqCallback() {
        return new RabbitCallback() {
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitAdviceFactory manualAcknowledgeAdvice() {
        return new DefaultRabbitManualAcknowledgeFactory();
    }

    /**
     * 非默认Rabbit监听器容器工厂
     *
     * @param connectionFactory   connectionFactory
     * @param rabbitAdviceFactory rabbitAdviceFactory
     * @param messageConverter    messageConverter
     * @return /
     * @see RabbitListenerAnnotationBeanPostProcessor#DEFAULT_RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME
     */
    @Bean(value = "manualAcknowledgeFactory")
    @ConditionalOnMissingBean(name = "manualAcknowledgeFactory")
    @ConditionalOnProperty(prefix = "force.rabbit.manual", name = "enable", havingValue = "true", matchIfMissing = true)
    public RabbitListenerContainerFactory<?> manualAcknowledgeFactory(ConnectionFactory connectionFactory,
                                                                      RabbitAdviceFactory rabbitAdviceFactory,
                                                                      MessageConverter messageConverter) {
        return manualFactory(connectionFactory, rabbitAdviceFactory, messageConverter);
    }

    /**
     * 设置手动提交工厂为默认工厂
     *
     * @param connectionFactory   connectionFactory
     * @param rabbitAdviceFactory rabbitAdviceFactory
     * @param messageConverter    messageConverter
     * @return /
     */
    @Bean(value = RabbitListenerAnnotationBeanPostProcessor.DEFAULT_RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    @ConditionalOnProperty(prefix = "force.rabbit.listener", name = "type", havingValue = "manual")
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                            RabbitAdviceFactory rabbitAdviceFactory,
                                                                            MessageConverter messageConverter) {
        return manualFactory(connectionFactory, rabbitAdviceFactory, messageConverter);
    }

    private SimpleRabbitListenerContainerFactory manualFactory(ConnectionFactory connectionFactory,
                                                               RabbitAdviceFactory rabbitAdviceFactory,
                                                               MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAdviceChain(rabbitAdviceFactory.getRabbitAdvice());
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory,
                                         RabbitCallback rabbitCallback,
                                         MessageConverter messageConverter) {
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(rabbitCallback);
        rabbitTemplate.setReturnCallback(rabbitCallback);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

}
