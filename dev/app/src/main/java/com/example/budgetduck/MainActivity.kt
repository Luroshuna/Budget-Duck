package com.example.budgetduck

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.view.marginStart
import java.util.*


class MainActivity : AppCompatActivity() {
    private val entries = Entries()
    private lateinit var fortune: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get Fortune field
        fortune = findViewById(R.id.fortune)
        fortune.setText(intent.getStringExtra("amount") ?: "0")

        //This should've been the code to load all the entries, but unfortunately it didn't work
        /*val test1: Entries = intent.getSerializableExtra("entries") as Entries ?: Entries()*/

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
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Are you sure you want to Reset?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ ->
                    entries.entries.clear()
                    fortune.setText("0")
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

        //Load entries
        loadEntries()
    }

    @SuppressLint("SetTextI18n")
    fun loadEntries() {
        //Creating Mock List so something can be displayed
        val testList = arrayListOf<Entry>()
        testList.add(Entry("159.98", "Games", true))
        testList.add(Entry("260", "Video Editing and Streaming", false))
        testList.add(Entry("35.60", "Food", true))
        testList.add(Entry("13.05", "Family and Friends", false))
        testList.add(Entry("1360", "Paycheck", false))
        testList.add(Entry("236.27", "Online Shopping", true))
        testList.add(Entry("46", "Music", true))

        //Get my entry table object
        val entryTable = findViewById<TableLayout>(R.id.entry_table)

        //Here I would've used entries but I couldn't get it to work

        if (testList.size > 0) {
            //Go through all objects of array and create a table row and the texts for every entry
            for (entry in testList) {
                //Initialize new View objects
                val newRow = TableRow(this)
                val newCategory = TextView(this)
                val newAmount = TextView(this)

                //Set Text and color, if it's an expanse it'll add a - to the amount to make it negative
                //Expenses will be marked red and revenues will be green
                newCategory.text = entry.category
                if (entry.isExpense){
                    newAmount.text = "-${entry.amount}"
                    newCategory.setTextColor(Color.parseColor("#FF0000"))
                    newAmount.setTextColor(Color.parseColor("#FF0000"))
                } else {
                    newAmount.text = entry.amount
                    newCategory.setTextColor(Color.parseColor("#00FF00"))
                    newAmount.setTextColor(Color.parseColor("#00FF00"))
                }

                //Category style
                newCategory.width = 275
                newCategory.textSize = 20F

                //Amount style
                newAmount.textSize = 20F
                newAmount.textAlignment = View.TEXT_ALIGNMENT_TEXT_END

                //Add row and texts to Activity
                entryTable.addView(newRow)
                newRow.addView(newCategory)
                newRow.addView(newAmount)
            }
        }
    }
}