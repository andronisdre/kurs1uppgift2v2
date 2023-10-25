package org.example;

public class Transaction {
    private double amount;
    private String date;
    private String title;


    //polymorphism below, overloaded polymorphism. Transaction can either take 3 inputs or 1. the second variation of the method is beneficial
    //due to it making it easier to identify if the Title given already exists as a key in expense/income.json, which in turn makes it easier to delete,
    //change or make sure that an added Transaction is unique
    public Transaction(double amount, String date, String title) {
        this.amount = amount;
        this.date = date;
        this.title = title;
    }

    public Transaction(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return ", Transaction" +
                " amount: " + amount +
                ", date: '" + date + '\'' +
                ", title: '" + title + '\'';
    }
}
