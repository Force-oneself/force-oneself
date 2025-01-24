package com.quan.demo.thread;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象 webhook 告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public abstract class WebhookAlarmSender implements AlarmSender {

    protected static final Logger logger = LoggerFactory.getLogger(WebhookAlarmSender.class);

    protected final String webhook;

    public WebhookAlarmSender(String webhook) {
        this.webhook = webhook;
    }

    @Override
    public void send(ThreadPoolMetrics metrics) {
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            // 构建JSON格式的请求体，包含线程池告警信息
            String jsonInputString = "{\"msgtype\": \"text\", \"text\": {\"content\": \"线程池告警: " + metrics + "\"}}";
            RequestBody body = RequestBody.create(jsonInputString, mediaType);

            Request request = new Request.Builder()
                    .url(webhook)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    logger.info("告警发送成功，Webhook: {}", webhook);
                } else {
                    String responseBody = response.body() != null ? response.body().string() : "无响应体";
                    logger.error("告警发送失败，Webhook: {}, 响应码: {}, 响应信息: {}", webhook, response.code(), responseBody);
                }
            } catch (Exception e) {
                logger.error("告警发送失败，Webhook: {}", webhook, e);
            }
        } catch (Exception e) {
            logger.error("告警发送失败", e);
        }
    }
}