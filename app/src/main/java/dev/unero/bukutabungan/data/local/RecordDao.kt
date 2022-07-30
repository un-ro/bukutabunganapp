package dev.unero.bukutabungan.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.unero.bukutabungan.data.model.Record

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    fun getAll(): LiveData<List<Record>>

    @Query("SELECT SUM(amount) FROM record WHERE isIncome == :isIncome")
    fun sumType(isIncome: Boolean): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: Record)
}