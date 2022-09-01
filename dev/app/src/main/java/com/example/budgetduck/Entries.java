package com.example.budgetduck;

import java.util.List;

public class Entries {
    List<Entry> entries;

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }
}
