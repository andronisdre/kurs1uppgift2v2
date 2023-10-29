package org.example;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner scanner = new Scanner(System.in);
    private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
    private static boolean keepGoing = true;
    private static IncomeStorage incomeStorage = new IncomeStorage();
    private static ExpenseStorage expenseStorage = new ExpenseStorage();

    public static void budgetTracker() throws IOException {
        incomeStorage.readFile(false, false);
        expenseStorage.readFile(false, false);
        System.out.println("Hello, do you wish to handle incomes or expenses?");
        System.out.println("Press 1 for income options and 2 for expense options");
        while (keepGoing) {
            try {
                int choice = scanner.nextInt();
                System.out.println("You chose option: " + choice);
                if (choice == 1) {
                    incomeChoices(choice);
                    keepGoing = false;
                } else if (choice == 2) {
                    expenseChoices(choice);
                    keepGoing = false;
                } else System.out.println("You need to choose 1 or 2! Choose again");
            } catch (Exception exception) {
                System.out.println("You need to write an integer!");
                System.out.println("Choose between 1 for income options and 2 for expense options again");
                scanner.nextLine();
            }
        }
    }

    public static void incomeChoices(int choice) throws IOException {
        System.out.println("What option regarding incomes do you want to look at?");
        System.out.println("1: add incomes, 2: remove incomes, 3: change incomes, 4: show all incomes, 5: show incomes subtracted by expenses, 6: search for an income, 7: show incomes in specified month");
        String transactionTitle;
        double transactionAmount;
        Income income;
        Income existing;
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        choice = scanner.nextInt();
        System.out.println("Chosen: " + choice);
        while (keepGoing) {
            switch (choice) {
                case 1:
                    System.out.println("Write title of income");
                    transactionTitle = scanner.next();
                    existing = new Income(transactionTitle);
                    if (incomeStorage.getIncomeList().containsKey(existing.getTitle())) {
                        System.out.println("The title of this income already exists! please write a unique title!");
                        System.out.println("_".repeat(50));
                        incomeStorage.readFile(true, false);
                        System.out.println("Write an income unique to the ones listed above");
                        break;
                    } else {
                        System.out.println("Write amount of income");
                        transactionAmount = scanner.nextDouble();
                        income = new Income(transactionAmount, DayMonthYear, (gregorianCalendar.get(Calendar.MONTH) + 1), transactionTitle);
                        incomeStorage.saveFile(income);
                        keepGoing = false;
                        break;
                    }
                case 2:
                    incomeStorage.readFile(true, false);
                    System.out.println("Type one of the keys in the list above to remove that income from the list");
                    transactionTitle = scanner.next();
                    existing = new Income(transactionTitle);
                    if (incomeStorage.getIncomeList().containsKey(existing.getTitle())) {
                        incomeStorage.removeFile(existing);
                        keepGoing = false;
                        break;
                    } else System.out.println("This key doesnt exist in the list!");
                    System.out.println("_".repeat(50));
                    break;
                case 3:
                    incomeStorage.readFile(true, false);
                    System.out.println("Type one of the keys in the list above to change that incomes values");
                    transactionTitle = scanner.next();
                    existing = new Income(transactionTitle);
                    if (incomeStorage.getIncomeList().containsKey(existing.getTitle())) {
                        System.out.println("Write new title of the income or the same");
                        transactionTitle = scanner.next();
                        System.out.println("Write new amount of transaction");
                        transactionAmount = scanner.nextDouble();
                        income = new Income(transactionAmount, DayMonthYear, (gregorianCalendar.get(Calendar.MONTH) + 1), transactionTitle);
                        incomeStorage.changeFile(existing, income);
                        keepGoing = false;
                        break;
                    } else System.out.println("This key doesnt exist in the list!");
                    System.out.println("_".repeat(50));
                    break;
                case 4:
                    incomeStorage.readFile(true, true);
                    keepGoing = false;
                    break;
                case 5:
                    incomeStorage.incomesSubtractedByExpenses();
                    keepGoing = false;
                    break;
                case 6:
                    System.out.println("Input the name of the income you want to look for");
                    String searchKey = scanner.next();
                    incomeStorage.searchIncomes(searchKey);
                    keepGoing = false;
                    break;
                case 7:
                    System.out.println("Input the number of the month you wish to see incomes for");
                    int month = scanner.nextInt();
                    incomeStorage.readIncomePerMonth(month);
                    keepGoing = false;
                    break;
                default:
                    System.out.println("You need to choose between option 1 through 7!");
                    choice = scanner.nextInt();
                    break;
            }
        }
    }

    public static void expenseChoices(int choice) throws IOException {
        System.out.println("What option regarding expenses do you want to look at?");
        System.out.println("1: add expenses, 2: remove expenses, 3: change expenses, 4: show all expenses, 5: show incomes subtracted by expenses, 6: search for an expense, 7: show expenses in specified month");
        String transactionTitle;
        double transactionAmount;
        Expense expense;
        Expense existing;
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        choice = scanner.nextInt();
        System.out.println("Chosen: " + choice);
        while (keepGoing) {
            switch (choice) {
                case 1:
                    System.out.println("Write title of expense");
                    transactionTitle = scanner.next();
                    existing = new Expense(transactionTitle);
                    if (expenseStorage.getExpenseList().containsKey(existing.getTitle())) {
                        System.out.println("The title of this expense already exists! please write a unique title!");
                        System.out.println("_".repeat(50));
                        expenseStorage.readFile(true, false);
                        System.out.println("Write an expense unique to the ones listed above");
                        break;
                    } else {
                        System.out.println("Write amount of expense");
                        transactionAmount = scanner.nextDouble();
                        expense = new Expense(transactionAmount, DayMonthYear, (gregorianCalendar.get(Calendar.MONTH) + 1), transactionTitle);
                        expenseStorage.saveFile(expense);
                        keepGoing = false;
                        break;
                    }
                case 2:
                    expenseStorage.readFile(true, false);
                    System.out.println("Type one of the keys in the list above to remove that expense from the list");
                    transactionTitle = scanner.next();
                    existing = new Expense(transactionTitle);
                    if (expenseStorage.getExpenseList().containsKey(existing.getTitle())) {
                        expenseStorage.removeFile(existing);
                        keepGoing = false;
                        break;
                    } else System.out.println("This key doesnt exist in the list!");
                    System.out.println("_".repeat(50));
                    break;
                case 3:
                    expenseStorage.readFile(true, false);
                    System.out.println("Type one of the keys in the list above to change that expenses values");
                    transactionTitle = scanner.next();
                    existing = new Expense(transactionTitle);
                    if (expenseStorage.getExpenseList().containsKey(existing.getTitle())) {
                        System.out.println("Write new title of the expense or the same");
                        transactionTitle = scanner.next();
                        System.out.println("Write new amount of transaction");
                        transactionAmount = scanner.nextDouble();
                        expense = new Expense(transactionAmount, DayMonthYear, (gregorianCalendar.get(Calendar.MONTH) + 1), transactionTitle);
                        expenseStorage.changeFile(existing, expense);
                        keepGoing = false;
                        break;
                    } else System.out.println("This key doesnt exist in the list!");
                    System.out.println("_".repeat(50));
                    break;
                case 4:
                    expenseStorage.readFile(true, true);
                    keepGoing = false;
                    break;
                case 5:
                    incomeStorage.incomesSubtractedByExpenses();
                    keepGoing = false;
                    break;
                case 6:
                    System.out.println("Input the name of the expense you want to look for");
                    String search = scanner.next();
                    expenseStorage.searchExpenses(search);
                    keepGoing = false;
                    break;
                case 7:
                    System.out.println("Input the number of the month you wish to see expenses for");
                    int month = scanner.nextInt();
                    expenseStorage.readExpensePerMonth(month);
                    keepGoing = false;
                    break;
                default:
                    System.out.println("You need to choose between option 1 through 7!");
                    choice = scanner.nextInt();
                    break;
            }
        }
    }
}

