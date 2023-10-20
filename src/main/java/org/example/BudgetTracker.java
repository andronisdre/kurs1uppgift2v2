package org.example;

import java.io.IOException;

public class BudgetTracker {
    public static void budgetTracker() throws IOException {
        Expense expense = new Expense(10, "10th october", EExpenseCategory.cheap, "exampleExpense");
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile();
        expenseStorage.saveFile(expense);
    }
}
