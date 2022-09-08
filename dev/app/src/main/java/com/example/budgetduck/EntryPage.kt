package com.example.budgetduck

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import java.io.Serializable


class EntryPage : AppCompatActivity() {
    private val entries = Entries()
    private lateinit var fortune: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_entry)

        //Get the amount of your fortune, this is the big number at the top of the screen on the main activity
        fortune = intent.getStringExtra("amount") ?: "0"

        //Get all the fields from the create entry activity
        val amount = findViewById<EditText>(R.id.editTextNumberDecimal)
        val category = findViewById<RadioGroup>(R.id.radio_group)
        val isExpense = findViewById<SwitchCompat>(R.id.switch1)

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

            //Check if an amount is entered
            if (TextUtils.isEmpty(amount.text)) {
                showError("No amount entered!")
                isValid = false
            }

            //Check if a category is selected
            val selectedRadioButtonId: Int = category.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                showError("No category selected!")
                isValid = false
            }

            //If both of the above is valid -> do the math and set the values
            if (isValid) {
                //Get all the values and push them into the array (sadly I couldn't get the pushed data to the other activity)
                entry.amount = amount.text.toString()
                val checkedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                entry.category = checkedRadioButton.text.toString()
                entry.isExpense = isExpense.isChecked
                entries.addEntry(entry)

                //Calculate the new fortune -> old fortune we get at the top of this onCreated() + the new entered amount
                calculateNewFortune()

                //Send the new calculated fortune to the other activity
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("amount", fortune)
                /*intent.putExtra("entries", entries)*/
                startActivity(intent)
            }
        }
    }

    private fun showError(message: String) {
        //If you would've entered something incorrect, you would get an error alert dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { _, _ -> }
        builder.show()
    }

    @SuppressLint("SetTextI18n")
    private fun calculateNewFortune() {
        //First get the last entry of the array -> this would be the amount you just entered
        var entry = 0.0
        val lastEntry = entries.getEntries().last()
        var lastFortune = 0.0

        //Try to parse the new entry and the old fortune
        try {
            lastFortune = fortune.toDouble()
            entry = lastEntry.amount.toDouble()
        } catch (e: Exception) {
            Toast.makeText(this, "There was an Error: $e", Toast.LENGTH_LONG).show()
        }

        //If you've checked the "It's an Expense" switch it will turn the entered amount negative
        if (lastEntry.isExpense) {
            entry *= -1
        }

        //Now calculate and set new fortune and convert back to string
        fortune = (lastFortune + entry).toString()
    }
}