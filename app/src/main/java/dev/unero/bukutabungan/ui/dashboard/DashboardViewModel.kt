package dev.unero.bukutabungan.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.domain.repository.RecordRepository

class DashboardViewModel(
    private val repository: RecordRepository
) : ViewModel() {
    fun getRecords(): LiveData<List<Record>> = repository.getAllRecord()
}