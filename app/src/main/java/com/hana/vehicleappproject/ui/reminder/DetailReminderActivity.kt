package com.hana.vehicleappproject.ui.reminder

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hana.vehicleappproject.data.local.entity.Reminder
import com.hana.vehicleappproject.databinding.ActivityDetailReminderBinding
import com.hana.vehicleappproject.viewmodel.ReminderViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailReminderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReminderBinding
    private val viewModel: ReminderViewModel by viewModels()
    private val calendar: Calendar = Calendar.getInstance()
    private var isEditMode = false
    private var reminder: Reminder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerima data Reminder yang dikirimkan dari MainActivity
        reminder = intent.getParcelableExtra("EXTRA_REMINDER")
        reminder?.let {
            populateReminderDetails(it)
        }

        setupListeners()
    }

    private fun populateReminderDetails(reminder: Reminder) {
        binding.etReminderTitle.setText(reminder.title)
        binding.etReminderDescription.setText(reminder.description)
        binding.tvDueDate.text = reminder.date
        binding.etReminderLocation.setText(reminder.location)
        binding.tvStatus.text = if (reminder.isCompleted) {
            "Status: Selesai"
        } else {
            "Status: Belum Selesai"
        }
        binding.tvStatus.setTextColor(
            if (reminder.isCompleted) {
                getColor(android.R.color.holo_green_dark)
            } else {
                getColor(android.R.color.holo_red_dark)
            }
        )
        toggleFields(!isEditMode)
    }

    private fun toggleFields(disable: Boolean) {
        binding.etReminderTitle.isEnabled = !disable
        binding.etReminderDescription.isEnabled = !disable
        binding.etReminderLocation.isEnabled = !disable
        binding.btnDatePicker.isEnabled = !disable
    }

    private fun setupListeners() {
        binding.btnEditSave.setOnClickListener {
            if (isEditMode) {
                saveReminderChanges()
            } else {
                if (reminder?.isCompleted == true || hasReminderPassed(reminder?.date)) {
                    Toast.makeText(
                        this,
                        "Pengingat sudah selesai atau melewati tanggal, tidak dapat diedit!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    isEditMode = true
                    binding.btnEditSave.text = "Simpan"
                    toggleFields(false)
                }
            }
        }

        binding.btnDelete.setOnClickListener {
            reminder?.let {
                viewModel.delete(it)
                Toast.makeText(this, "Pengingat berhasil dihapus!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun saveReminderChanges() {
        val updatedTitle = binding.etReminderTitle.text.toString().trim()
        val updatedDescription = binding.etReminderDescription.text.toString().trim()
        val updatedDate = binding.tvDueDate.text.toString().trim()
        val updatedLocation = binding.etReminderLocation.text.toString().trim()

        if (updatedTitle.isEmpty() || updatedDate.isEmpty()) {
            Toast.makeText(this, "Judul dan Tanggal tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        reminder?.let {
            it.title = updatedTitle
            it.description = updatedDescription
            it.date = updatedDate
            it.location = updatedLocation
            viewModel.update(it)
            Toast.makeText(this, "Pengingat berhasil diperbarui!", Toast.LENGTH_SHORT).show()
        }

        isEditMode = false
        binding.btnEditSave.text = "Edit"
        toggleFields(true)
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
        binding.tvDueDate.text = sdf.format(calendar.time)
    }

    private fun hasReminderPassed(date: String?): Boolean {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return try {
            val reminderDate = date?.let { dateFormat.parse(it) }
            reminderDate?.before(Calendar.getInstance().time) == true
        } catch (e: Exception) {
            false
        }
    }
}

