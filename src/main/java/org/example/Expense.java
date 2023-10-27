package org.example;

public class Expense extends Transaction {
    private EExpenseCategory eExpenseCategory;

    public Expense(double amount, String date, int month, String title) {
        super(amount, date, month, title);
        if (amount <= 100) {
            this.eExpenseCategory = EExpenseCategory.cheap;
        } else if (amount <= 1000) {
            this.eExpenseCategory = EExpenseCategory.medium;
        } else this.eExpenseCategory = EExpenseCategory.expensive;
    }

    public Expense(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return ", type: Expense {" +
                "Category: " + eExpenseCategory + super.toString() + '}';
    }
}
