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

    public Map<String, Income> getIncomeList() {
        return incomeList;
    }

    public void readFile(boolean andList, boolean andValues) throws IOException {
        Type type = new TypeToken<Map<String, Income>>(){}.getType();
        Reader reader = new FileReader(new File(fileName));
        incomeList = gson.fromJson(reader, type);
        if (andList) {
            boolean exists = false;
            System.out.println("Income List:");
            for(String name : incomeList.keySet()) {
                System.out.print("Key: " + name); if (andValues) System.out.print(incomeList.get(name));
                System.out.println();
                exists = true;
            }
            if (!exists) {
                System.out.println("There are no incomes in the list yet");
                System.out.println("You must add incomes first");
                BudgetTracker.setKeepGoing(false);
            }
        }
    }

    public void saveFile(Income income) throws IOException {
        incomeList.put(income.getTitle(), income);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(incomeList, fw);
        fw.close();
        System.out.println("Income saved!");
    }

    public void removeFile(Income income) throws IOException {
        incomeList.remove(income.getTitle());
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(incomeList, fw);
        fw.close();
        System.out.println("Income removed!");
    }

    public void changeFile(Income existing, Income income) throws IOException {
        //incomeList.replace(existing.getTitle(), income);
        incomeList.remove(existing.getTitle());
        incomeList.put(income.getTitle(), income);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(incomeList, fw);
        fw.close();
        System.out.println("Income changed!");
    }

    public void incomesSubtractedByExpenses() throws IOException {
        readFile(false, false);
        double totalAmountIncomes = 0;
        for (String name : incomeList.keySet()) {
            Income income = incomeList.get(name);
            totalAmountIncomes += income.getAmount();
        }
        System.out.println("Total amount incomes: " + totalAmountIncomes);
        ExpenseStorage expenseStorage = new ExpenseStorage();
        expenseStorage.readFile(false, false);
        double totalAmountExpenses = 0;
        for (String name : expenseStorage.getExpenseList().keySet()) {
            Expense expense = expenseStorage.getExpenseList().get(name);
            totalAmountExpenses += expense.getAmount();
        }
        System.out.println("Total amount expenses: " + totalAmountExpenses);
        System.out.println("Incomes subtracted by expenses: " + (totalAmountIncomes - totalAmountExpenses));
    }

    public void searchIncomes(String search) throws IOException {
        readFile(false, false);
        if (incomeList.containsKey(search)) {
            System.out.println("This income matches your input: Key: " + search + incomeList.get(search));
        } else {
            boolean exists = false;
            for(String name : incomeList.keySet()) {
                boolean startsWithSearchKey = name.toLowerCase().startsWith(search.toLowerCase());
                if (startsWithSearchKey) {
                    System.out.println("This income starts with your input: key: " + name + incomeList.get(name));
                    exists = true;
                }
            }
            if (!exists) {
                System.out.println("This income doesnt exist!");
                System.out.println("Press 1 if you wish to search again, press 2 to also see a list of all incomes, press 3 to quit program");
                int wantToContinue = BudgetTracker.scanner.nextInt();
                if (wantToContinue == 1 || wantToContinue == 2) {
                    if (wantToContinue == 2) {
                        readFile(true, false);
                    }
                    if (BudgetTracker.isKeepGoing()) {
                        System.out.println("Input the name of the income you want to look for");
                        search = BudgetTracker.scanner.next();
                        searchIncomes(search);
                    }
                }
            }
        }
    }

    public void readIncomePerMonth(int month) throws IOException {
        readFile(false, false);
        boolean monthHasIncomes = false;
        System.out.println("Incomes in month: " + month);
        for(String name : incomeList.keySet()) {
            if (incomeList.get(name).getMonth() == month) {
                System.out.print("Key: " + name); System.out.print(incomeList.get(name));
                System.out.println();
                monthHasIncomes = true;
            }
        }
        if (!monthHasIncomes) {
            System.out.println("No incomes in the month you typed!");
        }
    }
}

