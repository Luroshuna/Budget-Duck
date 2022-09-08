package com.example.budgetduck;

import java.io.Serializable;
import java.util.ArrayList;

public class Entries implements Serializable {
    ArrayList<Entry> entries;

    public Entries() {
        this.entries = new ArrayList<>();
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }
}
