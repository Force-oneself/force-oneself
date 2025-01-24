package com.quan.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件告警发送器
 *
 * @author Force-oneself
 * @date 2025-01-23
 */
public class EmailAlarmSender implements AlarmSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailAlarmSender.class);

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String from;
    private final String to;

    public EmailAlarmSender(String host, int port, String username, String password, String from, String to) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
        this.to = to;
    }

    @Override
    public void send(ThreadPoolMetrics metrics) {
        logger.info("开始发送邮件告警: {}", metrics);
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("线程池告警");
            message.setText("线程池告警: " + metrics);

            Transport.send(message);
            logger.info("邮件告警发送成功, 收件人: {}, 主题: {}", to, message.getSubject());
        } catch (MessagingException e) {
            logger.error("邮件告警发送失败", e);
        }
    }
}
