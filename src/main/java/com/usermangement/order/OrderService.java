package com.usermangement.order;

import com.usermangement.mail.MailService;

public class OrderService {
    private final MailService mailService;

    public OrderService(MailService mailService) {
        this.mailService = mailService;
    }

    public void createOrders() {
        mailService.sendMail("user@gmail.com", "Order Created");
    }
}
