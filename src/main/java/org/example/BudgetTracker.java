package org.example;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BudgetTracker {
    public static void budgetTracker() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input amount of expense");
        double transactionAmount = scanner.nextDouble();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        Expense expense = new Expense(transactionAmount, DayMonthYear, EExpenseCategory.cheap, "exampleExpense");
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile();
        System.out.println("press 1 to add expense, any other digit to remove expense");
        int choice = scanner.nextInt();
        if (choice == 1) {
            expenseStorage.saveFile(expense);
        } else expenseStorage.removeFile(expense);
    }
}
