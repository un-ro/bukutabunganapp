package dev.unero.bukutabungan.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unero.bukutabungan.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AccountRepository
): ViewModel() {
    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val realUsername = repository.getUsername()
            val realPassword = repository.getPassword()
            var isLogged = false

            realUsername.combine(realPassword) { realUs, realPs ->
                isLogged = (realUs == username && realPs == password)
            }

            _isLoggedIn.postValue(isLogged)
        }
    }
}