package com.example.budgetduck

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.get
import java.io.Console
import java.util.Map.entry

class EntryPage : AppCompatActivity() {
    private val entries = Entries()

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
                    finish()
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
            var isValid = true
            val entry = Entry("","",false)
            val amount = findViewById<EditText>(R.id.editTextNumberDecimal)
            val category = findViewById<RadioGroup>(R.id.radio_group)
            val isExpense = findViewById<SwitchCompat>(R.id.switch1)

            if (TextUtils.isEmpty(amount.text)) {
                showError("No amount entered!")
                isValid = false
            }

            val selectedRadioButtonId: Int = category.checkedRadioButtonId

            if (selectedRadioButtonId == -1) {
                showError("No category selected!")
                isValid = false
            }

            if(isValid){
                entry.amount = amount.text.toString()
                val checkedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                entry.category = checkedRadioButton.text.toString()
                entry.isExpense = isExpense.isActivated
                entries.addEntry(entry)

                Toast.makeText(this, "Entry successfully created!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun showError(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { _, _ -> }
        builder.show()
    }
}