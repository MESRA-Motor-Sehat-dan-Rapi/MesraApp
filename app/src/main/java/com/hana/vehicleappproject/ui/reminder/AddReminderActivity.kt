package com.hana.vehicleappproject.ui.reminder

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AddReminderActivity : AppCompatActivity() {
    private var dueDateTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        dueDateTextView = findViewById<TextView>(R.id.add_tv_due_date)
    }

    fun showDatePicker(view: View?) {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            this,
            { view1: DatePicker?, year1: Int, month1: Int, dayOfMonth: Int ->
                val selectedDate = dayOfMonth.toString() + "/" + (month1 + 1) + "/" + year1
                dueDateTextView!!.text = selectedDate
            }, year, month, day
        )

        datePickerDialog.show()
    }
}