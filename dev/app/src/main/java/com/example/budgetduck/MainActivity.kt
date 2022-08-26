package com.example.budgetduck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.add_btn)
        buttonClick.setOnClickListener {
            val intent = Intent(this, EntryPage::class.java)
            startActivity(intent)
        }
    }
}