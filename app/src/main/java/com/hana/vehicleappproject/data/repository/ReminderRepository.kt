package com.hana.vehicleappproject.data.repository

import androidx.lifecycle.LiveData
import com.hana.vehicleappproject.ui.reminder.Reminder
import com.hana.vehicleappproject.data.local.room.ReminderDao
import com.hana.vehicleappproject.data.local.room.ReminderDatabase

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
