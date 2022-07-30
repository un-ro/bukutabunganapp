package dev.unero.bukutabungan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.unero.bukutabungan.data.model.Record

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class RecordDatabase: RoomDatabase() {
    abstract fun getDao(): RecordDao
}