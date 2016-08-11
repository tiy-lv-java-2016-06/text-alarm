package com.theironyard.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * Created by vasantia on 8/10/16.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false)
    private String password;

    private String chargeId;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean paid = false;

    public User() {
    }

    public User(int phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
