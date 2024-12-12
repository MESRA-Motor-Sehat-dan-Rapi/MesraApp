package com.hana.vehicleappproject.data.dummy

data class Recommendation(
    val id: Int,
    val title: String,
    val description: String,
    val category: String
)

object RecommendationDummyData {
    val recommendations = listOf(
        Recommendation(
            id = 1,
            title = "Cek Ban",
            description = "Periksa tekanan ban secara berkala untuk menghindari kecelakaan.",
            category = "Ban"
        ),
        Recommendation(
            id = 2,
            title = "Ganti Oli",
            description = "Ganti oli mesin setiap 5000 km untuk menjaga performa kendaraan.",
            category = "Mesin"
        ),
        Recommendation(
            id = 3,
            title = "Bersihkan Kaca",
            description = "Gunakan kain lembut dan cairan pembersih untuk kaca motor.",
            category = "Kaca Motor"
        )
    )
}