package com.example.budgetduck

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.widget.SwitchCompat

class EntryPage : AppCompatActivity() {
    val entries = mutableListOf<Entries>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_entry)

        //Cancel Button
        val cancel = findViewById<Button>(R.id.cancel_btn)
        cancel.setOnClickListener {
            val builder = AlertDialog.Builder(this@EntryPage)
            builder.setMessage("Are you sure you want to Cancel?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        //Create Button
        val save = findViewById<Button>(R.id.save_btn)
        save.setOnClickListener {
            val entry: Entry
            val amount = findViewById<EditText>(R.id.editTextNumberDecimal)
            val category = findViewById<RadioGroup>(R.id.radio_group)
            val isExpense = findViewById<SwitchCompat>(R.id.switch1)

            if(TextUtils.isEmpty(amount.text)){

            } else {

            }


            Toast.makeText(this, "Entry successfully created!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}