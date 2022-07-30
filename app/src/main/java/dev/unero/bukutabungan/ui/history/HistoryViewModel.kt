package dev.unero.bukutabungan.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.domain.repository.RecordRepository

class HistoryViewModel(
    private val repository: RecordRepository
) : ViewModel() {
    fun getRecords(): LiveData<List<Record>> = repository.getAllRecord()
}