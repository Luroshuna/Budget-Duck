package com.example.budgetduck;

public class Entry {
    String amount;
    String category;
    Boolean isExpense;

    public Entry(String amount, String category, Boolean isExpense) {
        this.amount = amount;
        this.category = category;
        this.isExpense = isExpense;
    }
}
