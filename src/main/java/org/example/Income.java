package org.example;

public class Income extends Transaction {
    private EIncomeCategory eIncomeCategory;

    public Income(double amount, String date, EIncomeCategory eIncomeCategory) {
        super(amount, date);
        this.eIncomeCategory = EIncomeCategory.cheap;
    }
}
