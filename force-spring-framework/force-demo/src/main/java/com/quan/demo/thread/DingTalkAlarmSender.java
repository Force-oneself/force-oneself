package com.quan.demo.thread;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

/**
 * 钉钉机器人告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class DingTalkAlarmSender implements AlarmSender {

    private final String webhook;

    public DingTalkAlarmSender(String webhook) {
        this.webhook = webhook;
    }

    @Override
    public void send(ThreadPoolMetrics metrics) {
        DefaultDingTalkClient client = new DefaultDingTalkClient(webhook);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("线程池告警: " + metrics);
        request.setText(text);
        try {
            OapiRobotSendResponse response = client.execute(request);
            System.out.println("钉钉机器人告警发送结果: " + response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
