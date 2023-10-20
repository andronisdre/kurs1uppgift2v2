package org.example;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {
    public static void budgetTracker() throws IOException {
        Scanner scanner = new Scanner(System.in);
        double transactionAmount = scanner.nextDouble();
        Expense expense = new Expense(transactionAmount, "10th october", EExpenseCategory.cheap, "exampleExpense");
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile();
        int choice = scanner.nextInt();
        if (choice == 1) {
            expenseStorage.saveFile(expense);
        } else expenseStorage.removeFile(expense);
    }
}
