package com.quan.demo.thread;

/**
 * 钉钉机器人告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class DingTalkAlarmSender extends WebhookAlarmSender {

    public DingTalkAlarmSender(String webhook) {
        super(webhook);
    }
}