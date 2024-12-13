package com.hana.vehicleappproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String,
    var date: String,
    var location: String,
    var isChecked: Boolean = false,
    var isCompleted: Boolean = false
) : Parcelable

