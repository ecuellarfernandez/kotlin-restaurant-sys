package com.example.loginapi.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginapi.repositories.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val phone = MutableLiveData("")

    private val _registrationSuccess = MutableLiveData(false)
    val registrationSuccess: LiveData<Boolean> get() = _registrationSuccess

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage

    fun register() {
        viewModelScope.launch {
            UserRepository.registerUser(
                name.value ?: "",
                email.value ?: "",
                password.value ?: "",
                phone.value ?: "",
                getApplication(),
                success = {
                    if (it != null) {
                        _registrationSuccess.value = true
                    } else {
                        _errorMessage.value = "Registration failed"
                    }
                },
                failure = {
                    _errorMessage.value = it.message ?: "Unknown error"
                }
            )
        }
    }
}
