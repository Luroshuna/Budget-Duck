package com.example.budgetduck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val entries = Entries()
    private val allEntries = entries.getEntries()
    private lateinit var fortune: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fortune = findViewById(R.id.fortune)

        fortune.setText(intent.getStringExtra("amount") ?: "0")

        //Add entry Button
        val addEntry = findViewById<Button>(R.id.add_btn)
        addEntry.setOnClickListener {
            val intent = Intent(this, EntryPage::class.java)
            intent.putExtra("amount", fortune.text.toString())
            startActivity(intent)
        }

        //Reset Button
        val reset = findViewById<Button>(R.id.reset_btn)
        reset.setOnClickListener {
            entries.entries.clear()
            fortune.setText("0")
            finish()
            startActivity(intent)
        }
    }
}