package com.usermangement.mail;

public class GoogleMailService implements MailService {
    private String to;
    private String port;

    public GoogleMailService() {
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void sendMail(String to, String body) {
        System.out.println("Send by Google");
    }
}
