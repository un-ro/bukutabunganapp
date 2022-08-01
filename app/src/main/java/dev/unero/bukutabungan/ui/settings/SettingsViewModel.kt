package dev.unero.bukutabungan.ui.settings

import androidx.lifecycle.*
import dev.unero.bukutabungan.domain.repository.AccountRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: AccountRepository
) : ViewModel() {
    private var _isPasswordChanged = MutableLiveData<Boolean>()
    val isPasswordChanged: LiveData<Boolean> get() = _isPasswordChanged

    fun changePassword(newPassword: String) {
        viewModelScope.launch {
            repository.setPassword(newPassword)

            repository.getPassword().asLiveData().observeForever {
                _isPasswordChanged.postValue(newPassword == it)
            }
        }
    }

    var savedOldPassword = ""

    fun getOldPassword() {
        viewModelScope.launch {
            repository.getPassword().collect { savedOldPassword = it }
        }
    }
}