package org.example;

public class Transaction {
    //required fields amount and date. I decided to make date a String since I couldn't figure out how to save a Calendar to file
    private double amount;
    private String date;
    //I added the field month since date is a String and I needed an int for the readIncome/ExpensePerMonth method
    private int month;
    /*I added the field title to have a good variable to use for key in the map interfaces. It is also good for a short description
    of the Transaction. It fits well for the SearchIncomes/Expenses methods*/
    private String title;


    //polymorphism below, overloaded polymorphism. Constructor Transaction can either take 4 inputs or 1. the second variation of the method is beneficial
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

    //only made getters
    public double getAmount() {
        return amount;
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
