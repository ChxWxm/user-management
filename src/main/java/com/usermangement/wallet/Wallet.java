package com.usermangement.wallet;

public class Wallet {
    private final Integer id;
    private final String name;

    private final String email;

    public Wallet(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
