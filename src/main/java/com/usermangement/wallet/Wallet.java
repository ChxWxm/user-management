package com.usermangement.wallet;

public class Wallet {
    private final Integer id;
    private final String name;

    public Wallet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
