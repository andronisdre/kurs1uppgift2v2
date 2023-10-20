package org.example;

public class BudgetTracker {
    public static void budgetTracker() {
        System.out.println("budgetTracker method");
        Transaction transaction = new Transaction(0,"");
        System.out.println(transaction);
        Income income = new Income(10, "october 10th", EIncomeCategory.cheap);
        System.out.println(income);
        Expense expense = new Expense(5, "another date", EExpenseCategory.cheap);
        System.out.println(expense);
    }
}
