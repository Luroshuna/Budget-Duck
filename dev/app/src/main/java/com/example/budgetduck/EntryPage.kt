package com.example.budgetduck

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat


class EntryPage : AppCompatActivity() {
    private val entries = Entries()
    private var fortune: EditText = findViewById(R.id.fortune)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_entry)

        val amount = findViewById<EditText>(R.id.editTextNumberDecimal)
        val category = findViewById<RadioGroup>(R.id.radio_group)
        val isExpense = findViewById<SwitchCompat>(R.id.switch1)

        Toast.makeText(this, "Entry successfully created! ${fortune.text}", Toast.LENGTH_SHORT).show()

        //Cancel Button
        val cancel = findViewById<Button>(R.id.cancel_btn)
        cancel.setOnClickListener {
            val builder = AlertDialog.Builder(this@EntryPage)
            builder.setMessage("Are you sure you want to Cancel?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    finish()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        //Create Button
        val save = findViewById<Button>(R.id.save_btn)
        save.setOnClickListener {
            var isValid = true
            val entry = Entry("", "", false)

            if (TextUtils.isEmpty(amount.text)) {
                showError("No amount entered!")
                isValid = false
            }

            val selectedRadioButtonId: Int = category.checkedRadioButtonId

            if (selectedRadioButtonId == -1) {
                showError("No category selected!")
                isValid = false
            }

            if (isValid) {
                entry.amount = amount.text.toString()
                val checkedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                entry.category = checkedRadioButton.text.toString()
                entry.isExpense = isExpense.isActivated
                entries.addEntry(entry)

                calculateNewFortune()

                Toast.makeText(this, "Entry successfully created! ${fortune.text}", Toast.LENGTH_LONG).show()
                finish()
            }
        }

        //Change Switch text
        isExpense.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                isExpense.text = "It's an Expense"
            } else {
                isExpense.text = "It's an Revenue"
            }
        }
    }

    private fun showError(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { _, _ -> }
        builder.show()
    }

    @SuppressLint("SetTextI18n")
    private fun calculateNewFortune() {
        var entry = 0.0
        val lastEntry = entries.getEntries().last()
        var lastFortune = 0.0

        try {
            lastFortune = fortune.text.toString().toDouble()
            entry = lastEntry.amount.toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "There was an Error: $e", Toast.LENGTH_LONG).show()
        }

        if (lastEntry.isExpense) {
            entry *= -1
        }

        fortune.setText((lastFortune + entry).toString())
    }
}