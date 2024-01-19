package com.usermangement.mail;

public class OutlookMailService implements MailService {

    public OutlookMailService() {
    }

    @Override
    public void sendMail(String to, String body) {
        System.out.println("Send by Outlook");
    }
}
