package com.usermangement.mail;

import org.springframework.beans.factory.annotation.Value;

public class GoogleMailService implements MailService {
    @Value("${mail-provider.google.url}")
    private String url;
    @Value("${mail-provider.google.url-port}")
    private String port;


    public GoogleMailService() {
    }

    public void sendMail(String to, String body) {
        System.out.println("Send by Google" + " url: " + url + " port: " + port);
    }
}
