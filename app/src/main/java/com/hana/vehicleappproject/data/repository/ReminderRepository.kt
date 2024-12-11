package com.hana.vehicleappproject.data.repository

import androidx.lifecycle.LiveData
import com.hana.vehicleappproject.ui.reminder.Reminder
import com.hana.vehicleappproject.data.local.room.ReminderDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReminderRepository(private val reminderDao: ReminderDao) {

    fun getReminders(): LiveData<List<Reminder>> {
        return reminderDao.getAllReminders()
    }

    suspend fun addReminder(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.insert(reminder)
        }
    }

    suspend fun updateReminder(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.update(reminder)
        }
    }

    suspend fun deleteReminder(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.delete(reminder)
        }
    }
}
