package org.example;

//uses extends to inherit from class Transaction
public class Income extends Transaction {
    //creates an instantiation of enum EIncomeCategory
    private EIncomeCategory eIncomeCategory;

    public Income(double amount, String date, int month, String title) {
        super(amount, date, month, title);
        //enum eIncomeCategory will be decided based on the value of int amount
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
