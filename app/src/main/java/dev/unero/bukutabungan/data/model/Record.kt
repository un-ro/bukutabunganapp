package dev.unero.bukutabungan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Int,
    val type: String,
)
