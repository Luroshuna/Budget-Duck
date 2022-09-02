package com.example.budgetduck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val entries = Entries()
    private val allEntries = entries.getEntries()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Add entry Button
        val addEntry = findViewById<Button>(R.id.add_btn)
        addEntry.setOnClickListener {
            val intent = Intent(this, EntryPage::class.java)
            startActivity(intent)
        }
    }
}