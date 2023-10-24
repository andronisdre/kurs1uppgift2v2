package org.example;

public class Income extends Transaction {
    private EIncomeCategory eIncomeCategory;

    public Income(double amount, String date, String title) {
        super(amount, date, title);
        if (amount <= 100) {
            this.eIncomeCategory = EIncomeCategory.cheap;
        } else if (amount <= 1000) {
            this.eIncomeCategory = EIncomeCategory.medium;
        } else this.eIncomeCategory = EIncomeCategory.expensive;
    }

    public Income(String title) {
        super(title);
    }
    @Override
    public String toString() {
        return ", type: Income {" +
                "Category: " + eIncomeCategory + super.toString() + '}';
    }
}
