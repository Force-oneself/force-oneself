package com.quan.demo.thread;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class SmsAlarmSender implements AlarmSender {

    private static final Logger logger = LoggerFactory.getLogger(SmsAlarmSender.class);

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
        // 设置地域ID,短信服务对应的地域,比如国内常用的是"cn-hangzhou"
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        // 设置发送短信的电话号码,支持多个号码,以逗号分隔
        request.setPhoneNumbers(phoneNumbers);
        // 设置短信签名名称
        request.setSignName(signName);
        // 设置短信模板Code
        request.setTemplateCode(templateCode);
        // 设置短信模板中的变量参数,格式为JSON字符串,例如:{"code":"123456"}
        request.setTemplateParam("{}");

        SendSmsResponse response = client.getCommonResponse(request);
        if (response.getCode() != null && "OK".equals(response.getCode())) {
            logger.info("短信发送成功");
        } else {
            logger.error("短信发送失败,错误码:{} ,错误信息:{}", response.getCode(), response.getMessage());
        }
    }
}
