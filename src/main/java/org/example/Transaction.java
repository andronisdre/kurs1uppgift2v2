package org.example;

public class Transaction {
    private double amount;
    private String date;

    public Transaction(double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
