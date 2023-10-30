package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

public class ExpenseStorage {
    private Map<String, Expense> expenseList;
    private String fileName = "src/main/expense.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ExpenseStorage() {
    }

    public Map<String, Expense> getExpenseList() {
        return expenseList;
    }

    public void readFile(boolean andList, boolean andValues) throws IOException {
        Type type = new TypeToken<Map<String, Expense>>(){}.getType();
        Reader reader = new FileReader(new File(fileName));
        expenseList = gson.fromJson(reader, type);
        if (andList) {
            boolean exists = false;
            System.out.println("Expense List: ");
            for(String name : expenseList.keySet()) {
                System.out.print("Key: " + name); if (andValues) System.out.print(expenseList.get(name));
                System.out.println();
                exists = true;
            }
            if (!exists) {
                System.out.println("There are no expenses in the list yet");
                System.out.println("You must add expenses first");
                BudgetTracker.setKeepGoing(false);
            }
        }
    }

    public void saveFile(Expense expense) throws IOException {
        expenseList.put(expense.getTitle(), expense);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(expenseList, fw);
        fw.close();
        System.out.println("Expense saved!");
    }

    public void removeFile(Expense expense) throws IOException {
        expenseList.remove(expense.getTitle());
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(expenseList, fw);
        fw.close();
        System.out.println("Expense removed!");
    }

    public void changeFile(Expense existing, Expense expense) throws IOException {
        expenseList.remove(existing.getTitle());
        expenseList.put(expense.getTitle(), expense);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(expenseList, fw);
        fw.close();
        System.out.println("Expense changed!");
    }

    public void searchExpenses(String search) throws IOException {
        readFile(false, false);
        if (expenseList.containsKey(search)) {
            System.out.println("This expense matches your input: Key: " + search + expenseList.get(search));
        } else {
            boolean exists = false;
            for(String title : expenseList.keySet()) {
                boolean startsWithSearchKey = title.toLowerCase().startsWith(search.toLowerCase());
                if (startsWithSearchKey) {
                    System.out.println("This expense starts with your input: key: " + title + expenseList.get(title));
                    exists = true;
                }
            }
            if (!exists) {
                System.out.println("This expense doesnt exist!");
                System.out.println("Press 1 if you wish to search again, press 2 to also see a list of all expenses, press 3 to quit program");
                int wantToContinue = BudgetTracker.scanner.nextInt();
                if (wantToContinue == 1 || wantToContinue == 2) {
                    if (wantToContinue == 2) {
                        readFile(true, false);
                    }
                    if (BudgetTracker.isKeepGoing()) {
                        System.out.println("Input the name of the expense you want to look for");
                        search = BudgetTracker.scanner.next();
                        searchExpenses(search);
                    }
                }
            }
        }
    }

    public void readExpensePerMonth(int month) throws IOException {
        readFile(false, false);
        boolean monthHasExpenses = false;
        System.out.println("Expenses in month: " + month);
        for(String name : expenseList.keySet()) {
            if (expenseList.get(name).getMonth() == month) {
                System.out.print("Key: " + name); System.out.print(expenseList.get(name));
                System.out.println();
                monthHasExpenses = true;
            }
        }
        if (!monthHasExpenses) {
            System.out.println("No expenses in the month you typed!");
        }
    }
}
