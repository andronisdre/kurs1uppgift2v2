package org.example;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner scanner = new Scanner(System.in);
    private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
    private static boolean keepGoing = true;
    public static void budgetTracker() throws IOException {
        System.out.println("Hello, do you wish to handle incomes or expenses?");
        while (keepGoing) {
            try {
                System.out.println("press 1 for incomes and 2 for expenses");
                int choice = scanner.nextInt();
                System.out.println("you chose option: " + choice);
                if (choice == 1) {
                    incomeChoices(choice);
                    keepGoing = false;
                } else if (choice == 2) {
                    expenseChoices(choice);
                    keepGoing = false;
                } else System.out.println("you need to choose 1 or 2!");
            } catch (Exception exception) {
                System.out.println("you need to write an integer!");
                scanner.nextLine();
            }
        }
    }
    public static void incomeChoices(int choice) throws IOException {
        System.out.println("What option regarding incomes do you want to look at?");
        System.out.println("1: add incomes, 2: remove incomes, 3: change incomes, 4: show all incomes, 5: show incomes subtracted by expenses");
        IncomeStorage incomeStorage = new IncomeStorage();
        String titleOfExistingTransaction;
        String transactionTitle;
        double transactionAmount;
        Income income;
        Income existing;
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        choice = scanner.nextInt();
        while (keepGoing) {
            switch (choice) {
                case 1:
                    System.out.println("chosen: 1: add incomes");
                    System.out.println("write title of income");
                    transactionTitle = scanner.next();
                    System.out.println("write amount of transaction");
                    transactionAmount = scanner.nextDouble();
                    income = new Income(transactionAmount, DayMonthYear, transactionTitle);
                    incomeStorage.readFile(false);
                    incomeStorage.saveFile(income);
                    keepGoing = false;
                    break;
                case 2:
                    System.out.println("chosen: 2: remove incomes");
                    incomeStorage.readFile(true);
                    System.out.println("type one of the keys in the list above to remove that income from the list");
                    titleOfExistingTransaction = scanner.next();
                    existing = new Income(titleOfExistingTransaction);
                    incomeStorage.removeFile(existing);
                    keepGoing = false;
                    break;
                case 3:
                    System.out.println("chosen: 3: change incomes");
                    incomeStorage.readFile(true);
                    System.out.println("type one of the keys in the list above to change that incomes values");
                    titleOfExistingTransaction = scanner.next();
                    existing = new Income(titleOfExistingTransaction);
                    if (incomeStorage.getIncomeList().containsKey(existing.getTitle())) {
                        System.out.println("write new title of the income or the same");
                        transactionTitle = scanner.next();
                        System.out.println("write new amount of transaction");
                        transactionAmount = scanner.nextDouble();
                        income = new Income(transactionAmount, DayMonthYear, transactionTitle);
                        incomeStorage.changeFile(existing, income);
                        keepGoing = false;
                        break;
                    } else System.out.println("this key doesnt exist in the list!"); System.out.println("-------------------------------"); break;
                case 4:
                    System.out.println("chosen: 4: show all incomes");
                    incomeStorage.readFile(true);
                    keepGoing = false;
                    break;
                case 5:
                    System.out.println("chosen: 5: show incomes subtracted by expenses");
                    incomeStorage.incomesSubtractedByExpenses();
                    keepGoing = false;
                    break;
                default:
                    System.out.println("you need to choose between option 1 through 5!");
                    break;
            }
        }
    }
    public static void expenseChoices(int choice) throws IOException {
        System.out.println("What option regarding expenses do you want to look at?");
        System.out.println("1: add expenses, 2: remove expenses, 3: change expenses, 4: show all expenses, 5: show incomes subtracted by expenses");
        ExpenseStorage expenseStorage = new ExpenseStorage();
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("1: add expenses");
                System.out.println("write title of income");
                String transactionTitle = scanner.next();
                System.out.println("write amount of transaction");
                double transactionAmount = scanner.nextDouble();
                String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
                Expense expense = new Expense(transactionAmount, DayMonthYear, transactionTitle);
                expenseStorage.readFile(false);
                scanner.nextLine();
                expenseStorage.saveFile(expense);
                break;
            case 2:
                System.out.println("chosen: 2: remove expenses");
                expenseStorage.readFile(true);
                System.out.println("type one of the keys in the list above to remove that expense from the list");
                String titleOfTransaction = scanner.next();
                Expense existing = new Expense(titleOfTransaction);
                expenseStorage.removeFile(existing);
                break;
            case 3:
                System.out.println("3: change expenses");
                break;
            case 4:
                System.out.println("4: show all expenses");
                expenseStorage.readFile(true);
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
