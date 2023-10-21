package org.example;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BudgetTracker {
    public static void budgetTracker() throws IOException {
        System.out.println("Hello, do you wish to look at incomes or expenses?");
        Scanner scanner = new Scanner(System.in);
        System.out.println("input amount of expense");
        double transactionAmount = scanner.nextDouble();
        System.out.println("input title of transaction");
        String transactionTitle = scanner.next();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        Expense expense = new Expense(transactionAmount, DayMonthYear, EExpenseCategory.cheap, transactionTitle);
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile();
        System.out.println("press 1 to add expense, any other digit to remove expense");
        scanner.nextLine();
        int choice = scanner.nextInt();
        if (choice == 1) {
            expenseStorage.saveFile(expense);
        } else expenseStorage.removeFile(expense);
    }
}
