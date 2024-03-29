package com.usermangement;

import com.usermangement.mail.GoogleMailService;
import com.usermangement.mail.MailService;
import com.usermangement.mail.OutlookMailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Bean
    public MailService outlookMailService() {
        return new OutlookMailService();
    }

    @Bean
    public MailService googleMailService() {
        return new GoogleMailService();
    }
}
