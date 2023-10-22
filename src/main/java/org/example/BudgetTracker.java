package org.example;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner scanner = new Scanner(System.in);
    private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
    public static void budgetTracker() throws IOException {
        System.out.println("Hello, do you wish to handle incomes or expenses?");
        System.out.println("press 1 for incomes and 2 for expenses");
        int choice = scanner.nextInt();
        System.out.println("you chose option: " + choice);
        if (choice == 1) {
            incomeChoices(choice);
        } else if (choice == 2) {
            expenseChoices(choice);
        } else System.out.println("you need to choose 1or 2!");

        /*double transactionAmount = scanner.nextDouble();
        String transactionTitle = scanner.next();
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        Expense expense = new Expense(transactionAmount, DayMonthYear, EExpenseCategory.cheap, transactionTitle);
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile();
        System.out.println("press 1 to add expense, any other digit to remove expense");
        scanner.nextLine();
        choice = scanner.nextInt();
        if (choice == 1) {
            expenseStorage.saveFile(expense);
        } else expenseStorage.removeFile(expense);*/

    }
    public static void incomeChoices(int choice) throws IOException {
        System.out.println("What option regarding incomes do you want to look at?");
        System.out.println("1: add incomes, 2: remove incomes, 3: change incomes, 4: show all incomes, 5: show incomes subtracted by expenses");
        IncomeStorage incomeStorage = new IncomeStorage();
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("chosen: 1: add incomes");
                System.out.println("write title of income");
                String transactionTitle = scanner.next();
                System.out.println("write amount of transaction");
                double transactionAmount = scanner.nextDouble();
                String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
                Income income = new Income(transactionAmount, DayMonthYear, EIncomeCategory.cheap, transactionTitle);
                incomeStorage.readFile(false);
                scanner.nextLine();
                incomeStorage.saveFile(income);
                break;
            case 2:
                System.out.println("chosen: 2: remove incomes");
                incomeStorage.readFile(true);
                System.out.println("type one of the keys in the list above to remove that income from the list");
                String titleOfTransaction = scanner.next();
                Income income1 = new Income(titleOfTransaction);
                incomeStorage.removeFile(income1);
                break;
            case 3:
                System.out.println("chosen: 3: change incomes");
                break;
            case 4:
                System.out.println("chosen: 4: show all incomes");
                incomeStorage.readFile(true);
                break;
            case 5:
                System.out.println("chosen: 5: show incomes subtracted by expenses");
                break;
            default:
                System.out.println("you need to choose between option 1 through 5!");
                break;
        }
    }
    public static void expenseChoices(int choice) {
        System.out.println("What option regarding expenses do you want to look at?");
        System.out.println("1: add expenses, 2: remove expenses, 3: change expenses, 4: show all expenses, 5: show incomes subtracted by expenses");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("1: add expenses");
                break;
            case 2:
                System.out.println("2: remove expenses");
                break;
            case 3:
                System.out.println("3: change expenses");
                break;
            case 4:
                System.out.println("4: show all expenses");
                break;
            case 5:
                System.out.println("5: show incomes subtracted by expenses");
                break;
            default:
                System.out.println("you need to choose between option 1 through 5!");
                break;
        }
    }
}
