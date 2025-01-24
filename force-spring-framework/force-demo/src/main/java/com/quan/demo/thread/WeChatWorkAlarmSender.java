package com.quan.demo.thread;

/**
 * 企业微信机器人告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class WeChatWorkAlarmSender extends WebhookAlarmSender {

    public WeChatWorkAlarmSender(String webhook) {
        super(webhook);
    }
}