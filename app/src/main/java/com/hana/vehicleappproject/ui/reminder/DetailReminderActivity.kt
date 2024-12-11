package com.hana.vehicleappproject.ui.reminder

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hana.vehicleappproject.databinding.ActivityDetailReminderBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailReminderActivity : AppCompatActivity() {

    private var isEditMode = false
    private var reminderDate: Date? = null

    private var _binding: ActivityDetailReminderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set data awal
        val title = "Judul Contoh"
        val description = "Deskripsi contoh"
        reminderDate = Calendar.getInstance().time

        binding.etReminderTitle.setText(title)
        binding.etReminderDescription.setText(description)
        binding.tvDueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(reminderDate!!)

        checkReminderStatus()

        // Tombol Edit dan Simpan
        binding.btnEditSave.setOnClickListener {
            if (isEditMode) {
                saveReminder()
            } else {
                enableEditMode()
            }
        }

        // Tombol Hapus
        binding.btnDelete.setOnClickListener {
            deleteReminder()
        }

        // Pemilihan Tanggal
        binding.btnDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun enableEditMode() {
        isEditMode = true
        binding.etReminderTitle.isEnabled = true
        binding.etReminderDescription.isEnabled = true
        binding.btnEditSave.text = "Simpan"
    }

    private fun saveReminder() {
        isEditMode = false
        binding.etReminderTitle.isEnabled = false
        binding.etReminderDescription.isEnabled = false
        binding.btnEditSave.text = "Edit"

        // Simpan logika (misal ke database atau shared preferences)
        Toast.makeText(this, "Reminder berhasil disimpan", Toast.LENGTH_SHORT).show()
        checkReminderStatus()
    }

    private fun deleteReminder() {
        // Logika hapus (misal hapus dari database atau shared preferences)
        Toast.makeText(this, "Reminder berhasil dihapus", Toast.LENGTH_SHORT).show()
        finish() // Kembali ke halaman sebelumnya
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            reminderDate = selectedDate.time
            binding.tvDueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(reminderDate!!)
            checkReminderStatus()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun checkReminderStatus() {
        val currentDate = Calendar.getInstance().time
        if (reminderDate != null && reminderDate!!.before(currentDate)) {
            binding.tvStatus.text = "Status: Selesai"
            binding.tvStatus.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else {
            binding.tvStatus.text = "Status: Belum Selesai"
            binding.tvStatus.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }
    }
}
