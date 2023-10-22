package org.example;

public class Income extends Transaction {
    private EIncomeCategory eIncomeCategory;

    public Income(double amount, String date, EIncomeCategory eIncomeCategory, String title) {
        super(amount, date, title);
        this.eIncomeCategory = EIncomeCategory.cheap;
    }

    public Income(String title) {
        super(title);
    }
}
