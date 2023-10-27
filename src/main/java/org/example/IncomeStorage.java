package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

public class IncomeStorage {
    private Map<String, Income> incomeList;
    private String fileName = "src/main/income.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public IncomeStorage() {
    }

    public void readFile(boolean andList, boolean andValues) throws IOException {
        Type type = new TypeToken<Map<String, Income>>(){}.getType();
        Reader reader = new FileReader(new File(fileName));
        incomeList = gson.fromJson(reader, type);

        if (andList) {
            System.out.println("income List:");
            for(String name : incomeList.keySet()) {
                System.out.print("Key: " + name); if (andValues) System.out.print(incomeList.get(name));
                System.out.println();
            }
        }
    }


    public void saveFile(Income income) throws IOException {
        incomeList.put(income.getTitle(), income);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(incomeList, fw);
        fw.close();
        System.out.println("income saved!");
    }

    public void removeFile(Income income) throws IOException {
        incomeList.remove(income.getTitle());
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(incomeList, fw);
        fw.close();
        System.out.println("income removed!");
    }
    public void changeFile(Income existing, Income income) throws IOException {
        //incomeList.replace(existing.getTitle(), income);
        incomeList.remove(existing.getTitle());
        incomeList.put(income.getTitle(), income);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(incomeList, fw);
        fw.close();
        System.out.println("income changed!");
    }
    public void incomesSubtractedByExpenses() throws IOException {
        readFile(false, false);
        double totalAmountIncomes = 0;
        for (String name : incomeList.keySet()) {
            Income income = incomeList.get(name);
            totalAmountIncomes += income.getAmount();
        }
        System.out.println("total amount incomes: " + totalAmountIncomes);
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile(false, false);
        double totalAmountExpenses = 0;
        for (String name : expenseStorage.getExpenseList().keySet()) {
            Expense expense = expenseStorage.getExpenseList().get(name);
            totalAmountExpenses += expense.getAmount();
        }
        System.out.println("total amount expenses: " + totalAmountExpenses);
        System.out.println("incomes subtracted by expenses: " + (totalAmountIncomes - totalAmountExpenses));
    }

    public Map<String, Income> getIncomeList() {
        return incomeList;
    }
    public void searchIncomes(String search) throws IOException {
        readFile(false, false);
        if (incomeList.containsKey(search)) {
            System.out.println("this income matches your input: Key: " + search + incomeList.get(search));
        } else {
            boolean yesorno;
            boolean trueorfalse = false;
            for(String name : incomeList.keySet()) {
                yesorno = name.toLowerCase().startsWith(search.toLowerCase());
                if (yesorno) {
                    System.out.println("this income starts with your input: key: " + name + incomeList.get(name));
                    trueorfalse = true;
                }
            }
            if (!trueorfalse) {
                System.out.println("this income doesnt exist!");
                System.out.println("press 1 if you wish to search again, press 2 to also see a list of all incomes, press 3 to quit program");
                int wantToContinue = BudgetTracker.scanner.nextInt();
                if (wantToContinue == 1 || wantToContinue == 2) {
                    if (wantToContinue == 2) {
                        readFile(true, false);
                    }
                    System.out.println("input the name of the income you want to look for");
                    search = BudgetTracker.scanner.next();
                    searchIncomes(search);
                }
            }
        }
    }
    public void readIncomePerMonth(int month) throws IOException {
        readFile(false, false);
        System.out.println("incomes in month: " + month);
        boolean trueornah = false;
        for(String name : incomeList.keySet()) {
            if (incomeList.get(name).getMonth() == month) {
                System.out.print("Key: " + name); System.out.print(incomeList.get(name));
                System.out.println();
                trueornah = true;
            }
        }
        if (!trueornah) {
            System.out.println("no incomes in the month you typed!");
        }
    }
}

