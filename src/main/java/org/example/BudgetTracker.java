package org.example;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BudgetTracker {
    //I declare all these fields here so that they can be reached in the whole BudgetTracker class
    public static Scanner scanner = new Scanner(System.in);
    /*I use a GregorianCalendar to make a String DayMonthYear which will become the Date in Income and Expense.
    I also use it for the month field in income and expense*/
    private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
    //I use a boolean called keepGoing to loop the program in case of incorrect input
    private static boolean keepGoing = true;
    private static IncomeStorage incomeStorage = new IncomeStorage();
    private static ExpenseStorage expenseStorage = new ExpenseStorage();

    public static void budgetTracker() throws IOException {
        //I read in the files of respective Map interface
        incomeStorage.readFile(false, false);
        expenseStorage.readFile(false, false);
        System.out.println("Hello, do you wish to handle incomes or expenses?");
        System.out.println("Press 1 for income options and 2 for expense options");
        //Uses keepGoing in a while loop so that if the input is incorrect you will be given the prompt to write a new input
        //only if int choice is 1 or 2 will the while loop end
        while (keepGoing) {
            //if you input a non-integer it will be caught by the try catch
            try {
                int choice = scanner.nextInt();
                System.out.println("You chose option: " + choice);
                if (choice == 1) {
                    incomeChoices(choice);
                    //sets keepGoing to false
                    keepGoing = false;
                } else if (choice == 2) {
                    expenseChoices(choice);
                    keepGoing = false;
                    //doesnt set keepGoing to false
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
        //I declare variables here so that they can be used in the whole method
        String transactionTitle;
        double transactionAmount;
        Income income;
        Income existing;
        String DayMonthYear = ("Date added: month: " + (gregorianCalendar.get(Calendar.MONTH) + 1) + ", day: " + gregorianCalendar.get(Calendar.DATE) + ", year: " + gregorianCalendar.get(Calendar.YEAR));
        choice = scanner.nextInt();
        System.out.println("Chosen: " + choice);
        //while loop similar to that of budgetTrackers while loop
        while (keepGoing) {
            /*I could have added a try catch here as well so that if an incorrect input was given the program would
            loop this while loop instead of the one in budgetTracker method. I decided against it since that would
            imply that there's while loops and try catches at every input of the program, which would be a lot of code
            that clutters it up*/
            switch (choice) {
                case 1:
                    System.out.println("Write title of income");
                    transactionTitle = scanner.next();
                    existing = new Income(transactionTitle);
                    //in the add method, if the incomeList Map interface already contains a key identical to the
                    //one given by the user, they will have to write a new input
                    if (incomeStorage.getIncomeList().containsKey(existing.getTitle())) {
                        System.out.println("The title of this income already exists! please write a unique title!");
                        //instead of typing ____ repeatedly I use a String method which repeats the character for as many times you want
                        //I use _ to separate the message that input was incorrect and the output that gives you a list of keys
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
                    //if there are no Incomes in incomeStorage, keepGoing will have been set to false and end the program with the break;
                    if (!keepGoing) {
                        break;
                    }
                    System.out.println("Type one of the keys in the list above to remove that income from the list");
                    transactionTitle = scanner.next();
                    existing = new Income(transactionTitle);
                    //reverse of the if else statement in case 1:
                    if (incomeStorage.getIncomeList().containsKey(existing.getTitle())) {
                        incomeStorage.removeFile(existing);
                        keepGoing = false;
                        break;
                    } else System.out.println("This key doesnt exist in the list!");
                    System.out.println("_".repeat(50));
                    break;
                case 3:
                    incomeStorage.readFile(true, false);
                    if (!keepGoing) {
                        break;
                    }
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
                    //I comment on these methods in IncomeStorage.java
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
                    if (!keepGoing) {
                        break;
                    }
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
                    if (!keepGoing) {
                        break;
                    }
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

    //used to give access for respective Storages to set keepGoing
    public static void setKeepGoing(boolean keepGoing) {
        BudgetTracker.keepGoing = keepGoing;
    }

    //used to give access for respective Storages to get keepGoing
    public static boolean isKeepGoing() {
        return keepGoing;
    }
}

