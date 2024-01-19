package com.usermangement.mail;

public class OutlookMailService implements MailService {
    private String to;
    private String port;

    public OutlookMailService() {
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public void sendMail(String to, String body) {
        System.out.println("Send by Outlook");
    }
}
