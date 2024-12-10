package com.hana.vehicleappproject.ui.reminder

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hana.vehicleappproject.R
import java.text.SimpleDateFormat
import java.util.*

class DetailReminderActivity : AppCompatActivity() {

    private var isEditMode = false
    private var reminderDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_reminder)

        // Set data awal
        val title = "Judul Contoh"
        val description = "Deskripsi contoh"
        reminderDate = Calendar.getInstance().time

        etReminderTitle.setText(title)
        etReminderDescription.setText(description)
        tvDueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(reminderDate!!)

        checkReminderStatus()

        // Tombol Edit dan Simpan
        btnEditSave.setOnClickListener {
            if (isEditMode) {
                saveReminder()
            } else {
                enableEditMode()
            }
        }

        // Tombol Hapus
        btnDelete.setOnClickListener {
            deleteReminder()
        }

        // Pemilihan Tanggal
        btnDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun enableEditMode() {
        isEditMode = true
        etReminderTitle.isEnabled = true
        etReminderDescription.isEnabled = true
        btnEditSave.text = "Simpan"
    }

    private fun saveReminder() {
        isEditMode = false
        etReminderTitle.isEnabled = false
        etReminderDescription.isEnabled = false
        btnEditSave.text = "Edit"

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
            tvDueDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(reminderDate!!)
            checkReminderStatus()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun checkReminderStatus() {
        val currentDate = Calendar.getInstance().time
        if (reminderDate != null && reminderDate!!.before(currentDate)) {
            tvStatus.text = "Status: Selesai"
            tvStatus.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else {
            tvStatus.text = "Status: Belum Selesai"
            tvStatus.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }
    }
}
