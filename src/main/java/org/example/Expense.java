package org.example;

public class Expense extends Transaction {
    private EExpenseCategory eExpenseCategory;

    public Expense(double amount, String date, EExpenseCategory eExpenseCategory, String title) {
        super(amount, date, title);
        this.eExpenseCategory = EExpenseCategory.cheap;
    }
}
