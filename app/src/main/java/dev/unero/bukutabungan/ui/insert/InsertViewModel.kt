package dev.unero.bukutabungan.ui.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.domain.repository.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertViewModel(
    private val repository: RecordRepository
) : ViewModel() {
    fun insertRecord(record: Record) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRecord(record)
    }
}