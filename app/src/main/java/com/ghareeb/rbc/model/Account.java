package com.ghareeb.rbc.model;

public class Account {

    private double balance;
    private String email;

    public Account(){

    }
    public Account(double balance, String email) {
        this.balance = balance;
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
