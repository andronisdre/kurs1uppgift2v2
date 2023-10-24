package org.example;

public class Transaction {
    private double amount;
    private String date;
    private String title;



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
