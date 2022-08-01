package dev.unero.bukutabungan.ui.login

import androidx.lifecycle.*
import dev.unero.bukutabungan.domain.repository.AccountRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AccountRepository
): ViewModel() {
    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val flowUsername = repository.getUsername()
            val flowPassword = repository.getPassword()

            flowUsername.asLiveData().observeForever { realUsername ->
                flowPassword.asLiveData().observeForever { realPassword ->
                    _isLoggedIn.postValue(
                        realUsername == username && realPassword == password
                    )
                }
            }
        }
    }

    fun setStatus(status: Boolean) {
        viewModelScope.launch { repository.setLoginStatus(status) }
    }

    suspend fun getStatus(): LiveData<Boolean> = repository.getLoginStatus().asLiveData()
}