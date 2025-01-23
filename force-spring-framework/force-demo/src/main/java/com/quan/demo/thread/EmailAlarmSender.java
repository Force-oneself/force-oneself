package com.quan.demo.thread;

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
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
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
            System.out.println("邮件告警发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
