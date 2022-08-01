package dev.unero.bukutabungan.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.domain.repository.AccountRepository
import dev.unero.bukutabungan.domain.repository.RecordRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: RecordRepository,
    private val accountSetting: AccountRepository,
) : ViewModel() {
    fun getRecords(): LiveData<List<Record>> = repository.getAllRecord()
    fun setStatus(status: Boolean) {
        viewModelScope.launch { accountSetting.setLoginStatus(status) }
    }
}