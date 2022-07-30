package dev.unero.bukutabungan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.unero.bukutabungan.data.model.Record

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    fun getAll(): List<Record>

    @Query("SELECT SUM(amount) FROM record WHERE type == :type")
    fun sumType(type: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(record: Record)
}