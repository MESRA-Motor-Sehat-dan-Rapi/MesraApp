package com.hana.vehicleappproject.ui.reminder

import androidx.lifecycle.LiveData

class ReminderRepository {

    private val reminderDao: ReminderDao = ReminderDatabase.getDatabase().reminderDao()

    fun getReminders(): LiveData<List<Reminder>> {
        return reminderDao.getAllReminders()
    }

    fun addReminder(reminder: Reminder) {
        reminderDao.insert(reminder)
    }

    fun updateReminder(reminder: Reminder) {
        reminderDao.update(reminder)
    }

    fun deleteReminder(reminder: Reminder) {
        reminderDao.delete(reminder)
    }
}
