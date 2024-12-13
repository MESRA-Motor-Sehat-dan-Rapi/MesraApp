package com.hana.vehicleappproject.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hana.vehicleappproject.data.local.entity.Reminder

@Dao
interface ReminderDao {

    // Mendapatkan semua pengingat (Reminder) dari tabel
    @Query("SELECT * FROM reminders")
    fun getAllReminders(): LiveData<List<Reminder>>

    // Mendapatkan pengingat yang belum diceklis (isChecked = 0)
    @Query("SELECT * FROM reminders WHERE isChecked = 0")
    fun getUncheckedReminders(): LiveData<List<Reminder>>

    // Menambahkan pengingat baru ke tabel
    @Insert
    suspend fun insert(reminder: Reminder)

    // Memperbarui pengingat yang sudah ada
    @Update
    suspend fun update(reminder: Reminder)

    // Menghapus pengingat dari tabel
    @Delete
    suspend fun delete(reminder: Reminder)
}
