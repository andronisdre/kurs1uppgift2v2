package org.example;

import java.io.IOException;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws IOException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println(gregorianCalendar.getTime());
        BudgetTracker.budgetTracker();
    }
}