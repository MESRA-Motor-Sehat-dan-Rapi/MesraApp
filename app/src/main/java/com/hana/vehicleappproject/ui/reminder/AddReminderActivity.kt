package com.hana.vehicleappproject.ui.reminder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.databinding.ActivityAddReminderBinding
import com.hana.vehicleappproject.viewmodel.ReminderViewModel

class AddReminderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReminderBinding
    private val viewModel: ReminderViewModel by viewModels() // Inisialisasi ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etReminderTitle.text.toString()
            val description = binding.etReminderDescription.text.toString() // Ambil input deskripsi
            val date = binding.addTvDueDate.text.toString()
            val location = binding.etReminderLocation.text.toString() // Ambil input lokasi

            if (title.isNotEmpty() && date.isNotEmpty() && location.isNotEmpty()) {
                val reminder = Reminder(
                    title = title,
                    description = description, // Masukkan deskripsi ke objek Reminder
                    date = date,
                    location = location, // Masukkan lokasi ke objek Reminder
                    isCompleted = false
                )
                viewModel.insert(reminder)
                Toast.makeText(this, "Reminder Added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}
