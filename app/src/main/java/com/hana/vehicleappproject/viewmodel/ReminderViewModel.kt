package com.hana.vehicleappproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hana.vehicleappproject.ui.reminder.Reminder
import com.hana.vehicleappproject.data.local.room.ReminderDatabase
import com.hana.vehicleappproject.data.repository.ReminderRepository
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReminderRepository
    val allReminders: LiveData<List<Reminder>>

    init {
        val reminderDao = ReminderDatabase.getDatabase(application).reminderDao()
        repository = ReminderRepository(reminderDao)
        allReminders = repository.allReminder
    }

    fun insert(reminder: Reminder) = viewModelScope.launch {
        repository.insert(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch {
        repository.update(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch {
        repository.delete(reminder)
    }

    fun getReminderById(id: Int) = viewModelScope.launch {
        repository.getReminderById(id)
    }
}

