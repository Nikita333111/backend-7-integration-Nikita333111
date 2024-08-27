package com.microservices.mailing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${gmail.username}")
    private String gmailFrom;
/*    @Value("${gmail.to}")
    private String gmailTo;*/

    public void sendEmailMessage(String to,String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(gmailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
