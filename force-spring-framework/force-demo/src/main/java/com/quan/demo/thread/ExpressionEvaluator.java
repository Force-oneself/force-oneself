package com.quan.demo.thread;

import org.mvel2.MVEL;

import java.util.Map;

/**
 * 表达式计算
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class ExpressionEvaluator {

    /**
     * 简单的表达式求值逻辑
     * 这里仅支持加减乘除和括号
     * 实现一个简单的栈来处理表达式
     * 为了简化，这里假设输入的表达式是有效的
     *
     * @param expression 表达式
     * @param variables  占位符变量
     * @return /
     */
    public boolean evaluate(String expression, Map<String, Object> variables) {

        // 将表达式中的变量替换为实际值
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            expression = expression.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue().toString());
        }

        // 使用 MVEL 来求值
        try {
            return (boolean) MVEL.eval(expression, variables);
        } catch (Exception e) {
            throw new RuntimeException("Failed to evaluate expression: " + expression, e);
        }
    }
}