package com.start.kit.components;

import javax.mail.MessagingException;

public interface SenderNotification {
    void sendMail(String from, String pass, String host, String port, String[] to, String subject, String body) throws MessagingException;
}
