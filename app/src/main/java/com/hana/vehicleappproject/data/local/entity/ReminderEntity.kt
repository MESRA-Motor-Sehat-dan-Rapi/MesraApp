package com.hana.vehicleappproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,             // ID unik untuk setiap reminder
    val title: String,           // Judul pengingat
    val description: String?,    // Deskripsi atau detail tambahan (opsional)
    val time: Long,              // Waktu pengingat dalam format timestamp (epoch time)
    val isCompleted: Boolean = false // Status apakah pengingat telah selesai
)