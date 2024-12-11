package com.hana.vehicleappproject.ui.reminder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.viewmodel.ReminderViewModel

class AddReminderActivity : AppCompatActivity() {
    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        btnSave.setOnClickListener {
            val title = etReminderTitle.text.toString()
            val description = etReminderDescription.text.toString()
            val date = add_tv_due_date.text.toString()

            if (title.isNotEmpty() && date.isNotEmpty()) {
                val reminder = Reminder(title = title, description = description, date = date, isCompleted = false)
                viewModel.insert(reminder)
                Toast.makeText(this, "Reminder Added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}
