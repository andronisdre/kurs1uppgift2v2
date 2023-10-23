package org.example;

public class Expense extends Transaction {
    private EExpenseCategory eExpenseCategory;

    public Expense(double amount, String date, String title) {
        super(amount, date, title);
        if (amount <= 100) {
            this.eExpenseCategory = EExpenseCategory.cheap;
        } else if (amount <= 1000) {
            this.eExpenseCategory = EExpenseCategory.medium;
        } else this.eExpenseCategory = EExpenseCategory.expensive;
    }

    public Expense(String title) {
        super(title);
    }
}
