// File: Recommendation.kt (di package com.hana.vehicleappproject.data.local.entity)
package com.hana.vehicleappproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Data class untuk merepresentasikan entitas rekomendasi
// Menggunakan anotasi @Entity agar dapat digunakan dalam Room Database
@Entity(tableName = "recommendations")
data class Recommendation(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val category: String
)
