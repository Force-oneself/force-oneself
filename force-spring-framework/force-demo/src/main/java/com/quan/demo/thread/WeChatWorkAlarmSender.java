package com.quan.demo.thread;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 * 企业微信机器人告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class WeChatWorkAlarmSender implements AlarmSender {

    private final String webhook;

    public WeChatWorkAlarmSender(String webhook) {
        this.webhook = webhook;
    }

    @Override
    public void send(ThreadPoolMetrics metrics) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("msgtype", "text");
        JSONObject text = new JSONObject();
        text.put("content", "线程池告警: " + metrics);
        json.put("text", text);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Request request = new Request.Builder()
                .url(webhook)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println("企业微信机器人告警发送结果: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
