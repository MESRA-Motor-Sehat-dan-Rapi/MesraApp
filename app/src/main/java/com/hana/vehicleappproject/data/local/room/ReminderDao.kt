package com.hana.vehicleappproject.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hana.vehicleappproject.ui.reminder.Reminder

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminder ORDER BY id DESC")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Query("SELECT * FROM reminder WHERE id = :id")
    suspend fun getReminderById(id: Int): Reminder
}
