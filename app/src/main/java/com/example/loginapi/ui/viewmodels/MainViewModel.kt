package com.example.loginapi.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapi.repositories.PreferencesRepository
import com.example.loginapi.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val errorMessage: LiveData<String> get() = _errorMessage

    suspend fun login(email: String, password: String, context: Context) {
        UserRepository.doLogin(email,
            password,
            context,
            success = {
                if (it == null) {
                    _errorMessage.value = "Usuario o contraseña incorrectos"
                    return@doLogin
                }
                _errorMessage.value = ""
                Log.d("MainViewModel", "Token: ${it.access_token}")
                val token: String = it.access_token!!
                PreferencesRepository.saveToken(token, context)
            }, failure = {
                it.printStackTrace()
            })
    }

    suspend fun register(name: String, email: String, password: String, phone: String, context: Context) {
        UserRepository.registerUser(name, email, password, phone, context,
            success = {
                if (it == null) {
                    _errorMessage.value = "Error al registrar usuario"
                    return@registerUser
                }
                _errorMessage.value = "Registro exitoso"
                Log.d("MainViewModel", "Token: ${it.access_token}")
                val token: String = it.access_token!!
                PreferencesRepository.saveToken(token, context)
            }, failure = {
                it.printStackTrace()
            })
    }
}
