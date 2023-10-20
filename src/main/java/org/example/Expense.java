package org.example;

public class Expense extends Transaction {
    private EExpenseCategory eExpenseCategory;

    public Expense(double amount, String date, EExpenseCategory eExpenseCategory) {
        super(amount, date);
        this.eExpenseCategory = EExpenseCategory.cheap;
    }
}
