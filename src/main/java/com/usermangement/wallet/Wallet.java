package com.usermangement.wallet;

import com.usermangement.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "wallet_name")
    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "profile_email", referencedColumnName = "email")
    private Profile profile;

    public Wallet() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
