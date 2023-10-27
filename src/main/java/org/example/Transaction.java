package org.example;

public class Transaction {
    private double amount;
    private String date;
    private int month;
    private String title;


    //polymorphism below, overloaded polymorphism. Transaction can either take 3 inputs or 1. the second variation of the method is beneficial
    //due to it making it easier to identify if the Title given already exists as a key in expense/income.json, which in turn makes it easier to delete,
    //change or make sure that an added Transaction is unique
    public Transaction(double amount, String date, int month, String title) {
        this.amount = amount;
        this.date = date;
        this.month = month;
        this.title = title;
    }

    public Transaction(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
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

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return ", Transaction" +
                " amount: " + amount +
                ", date: '" + date + '\'' +
                ", title: '" + title + '\'';
    }
}
