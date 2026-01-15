package com.mohamedbob.estate.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String adminEmail, String userEmail, String userName, String propertyTitle, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(adminEmail);
        mailMessage.setText(
                "A user just send a message :\n\n"+
                        "Name : " + userName + "\n" +
                        "email : " + userEmail + "\n" +
                        "Property : " + propertyTitle + "\n" +
                        "message : \n" + message

        );
        mailSender.send(mailMessage);
    }
}
