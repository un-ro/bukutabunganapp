package dev.unero.bukutabungan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.unero.bukutabungan.data.model.Record

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    fun getAll(): List<Record>

    @Insert
    fun insert(record: Record)
}