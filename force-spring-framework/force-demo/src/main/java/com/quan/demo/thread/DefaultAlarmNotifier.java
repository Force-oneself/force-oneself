package com.quan.demo.thread;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 告警通知默认实现
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class DefaultAlarmNotifier implements AlarmNotifier {

    /**
     * 动态线程池配置
     */
    private final DynamicThreadPoolProperties dynamicThreadPoolProperties;

    /**
     * 表达式计算器
     */
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    public DefaultAlarmNotifier(DynamicThreadPoolProperties dynamicThreadPoolProperties) {
        this.dynamicThreadPoolProperties = dynamicThreadPoolProperties;
    }

    @Override
    public boolean trigger(ThreadPoolMetrics metrics) {
        return Optional.ofNullable(dynamicThreadPoolProperties.getThreadPools().get(metrics.getName()))
                .map(ThreadPoolProperties::getTriggerExpression)
                .filter(StringUtils::hasText)
                .map(el -> this.evaluateExpression(el, metrics))
                .orElse(false);
    }

    @Override
    public void notify(ThreadPoolMetrics metrics) {
        // 不满足触发条件
        if (!trigger(metrics)) {
            return;
        }
        //TODO 触发告警，钉钉、企业微信、邮件、短信等
    }

    /**
     * 执行表达式运算
     *
     * @param expression 表达式
     * @param metrics    线程池监控信息
     * @return /
     */
    private boolean evaluateExpression(String expression, ThreadPoolMetrics metrics) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("activeCount", metrics.getActiveCount());
        variables.put("corePoolSize", metrics.getCorePoolSize());
        variables.put("queueSize", metrics.getQueueSize());
        variables.put("blockingTaskCount", (int) (metrics.getTaskCount() - metrics.getCompletedTaskCount()));
        return evaluator.evaluate(expression, variables);
    }

}
