package com.example.budgetduck;

import java.util.ArrayList;
import java.util.List;

public class Entries {
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
