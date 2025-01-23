package com.quan.demo.thread;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;

/**
 * 短信告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class SmsAlarmSender implements AlarmSender {

    private final String accessKeyId;
    private final String accessKeySecret;
    private final String signName;
    private final String templateCode;
    private final String phoneNumbers;

    public SmsAlarmSender(String accessKeyId, String accessKeySecret, String signName, String templateCode, String phoneNumbers) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.signName = signName;
        this.templateCode = templateCode;
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public void send(ThreadPoolMetrics metrics) {
        try {
            Config config = new Config()
                    .setAccessKeyId(accessKeyId)
                    .setAccessKeySecret(accessKeySecret);
            Client client = new Client(config);
            SendSmsRequest request = new SendSmsRequest()
                    .setSignName(signName)
                    .setTemplateCode(templateCode)
                    .setPhoneNumbers(phoneNumbers)
                    .setTemplateParam("{\"content\":\"线程池告警: " + metrics + "\"}");
            SendSmsResponse response = client.sendSms(request);
            System.out.println("短信告警发送结果: " + response.getBody().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
