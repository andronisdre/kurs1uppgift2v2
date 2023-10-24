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

    public void readFile(boolean andList, boolean andValues) throws IOException {
        Type type = new TypeToken<Map<String, Expense>>(){}.getType();
        Reader reader = new FileReader(new File(fileName));
        expenseList = gson.fromJson(reader, type);
        if (andList) {
            System.out.println("expense List: ");
            for(String name : expenseList.keySet()) {
                System.out.print("Key: " + name); if (andValues) System.out.print(expenseList.get(name));
                System.out.println();
            }
        }
    }


    public void saveFile(Expense expense) throws IOException {
        expenseList.put(expense.getTitle(), expense);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(expenseList, fw);
        fw.close();
        System.out.println("expense saved!");
    }

    public void removeFile(Expense expense) throws IOException {
        expenseList.remove(expense.getTitle());
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(expenseList, fw);
        fw.close();
        System.out.println("expense removed!");
    }
    public void changeFile(Expense existing, Expense expense) throws IOException {
        expenseList.remove(existing.getTitle());
        expenseList.put(expense.getTitle(), expense);
        FileWriter fw = new FileWriter(new File(fileName));
        gson.toJson(expenseList, fw);
        fw.close();
        System.out.println("expense changed!");
    }
    public void searchExpenses() {

    }

    public Map<String, Expense> getExpenseList() {
        return expenseList;
    }
}
