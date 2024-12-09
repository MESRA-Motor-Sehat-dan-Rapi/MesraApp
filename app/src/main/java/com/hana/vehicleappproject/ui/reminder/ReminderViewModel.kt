package com.hana.vehicleappproject.ui.reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ReminderViewModel : ViewModel() {
    private val reminderRepository = ReminderRepository() // Mengakses repository
    val reminders: LiveData<List<Reminder>> = reminderRepository.getReminders()

    fun addReminder(reminder: Reminder) {
        reminderRepository.addReminder(reminder)
    }

    fun updateReminder(reminder: Reminder) {
        reminderRepository.updateReminder(reminder)
    }

    fun deleteReminder(reminder: Reminder) {
        reminderRepository.deleteReminder(reminder)
    }
}
