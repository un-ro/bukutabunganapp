package dev.unero.bukutabungan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Int,
    val description: String,
    val date: String,
    val isIncome: Boolean,
)
