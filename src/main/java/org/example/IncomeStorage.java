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

    public void readFile() throws IOException {
        Type type = new TypeToken<Map<String, Income>>(){}.getType();
        Reader reader = new FileReader(new File(fileName));
        incomeList = gson.fromJson(reader, type);

        System.out.println("income List:");
        for(String name : incomeList.keySet()) {
            System.out.println("Key: " + name);
        }
        System.out.println("incomeList size " + incomeList.size());
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
}
