package com.hana.vehicleappproject.ui.reminder

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.hana.vehicleappproject.data.local.entity.Reminder
import com.hana.vehicleappproject.databinding.ActivityAddReminderBinding
import com.hana.vehicleappproject.viewmodel.ReminderViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddReminderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddReminderBinding
    private val viewModel: ReminderViewModel by viewModels()
    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // Handle save button click
        binding.btnSave.setOnClickListener {
            val title = binding.etReminderTitle.text.toString().trim()
            val description = binding.etReminderDescription.text.toString().trim()
            val date = binding.addTvDueDate.text.toString().trim()
            val location = binding.etReminderLocation.text.toString().trim()

            if (title.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
                if (title.isEmpty()) {
                    binding.etReminderTitle.error = "Title is required!"
                }
                if (description.isEmpty()) {
                    binding.etReminderDescription.error = "Description is required!"
                }
                if (date.isEmpty()) {
                    binding.addTvDueDate.error = "Date is required!"
                    Toast.makeText(this, "Date is required!", Toast.LENGTH_SHORT).show()
                }
                if (location.isEmpty()) {
                    binding.etReminderLocation.error = "Location is required!"
                }
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi format tanggal
            if (!isValidDateFormat(date)) {
                binding.addTvDueDate.error = "Format tanggal salah! Gunakan format: dd MMM yyyy"
                Toast.makeText(this, "Format tanggal salah! Gunakan format: dd MMM yyyy", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val reminder = Reminder(
                title = title,
                description = description,
                date = date,
                location = location
            )

            viewModel.insert(reminder)
            Toast.makeText(this, "Pengingat berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Handle cancel button click
        binding.btnCancel.setOnClickListener {
            finish()
        }

        // Handle date picker button click
        binding.btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                updateDateLabel()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun updateDateLabel() {
        val dateFormat = "dd MMM yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        binding.addTvDueDate.text = sdf.format(calendar.time)
    }

    // Fungsi validasi format tanggal
    private fun isValidDateFormat(date: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            sdf.isLenient = false // Non-lenient: format harus cocok 100%
            sdf.parse(date)
            true
        } catch (e: ParseException) {
            e.printStackTrace()
            false
        }
    }

    fun showDatePicker(view: View) {}
}
