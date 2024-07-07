package com.example.loginapi.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginapi.models.dto.ReservationResponseDTO
import com.example.loginapi.repositories.PreferencesRepository
import com.example.loginapi.repositories.ReservationRepository
import kotlinx.coroutines.launch

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    private val _reservations = MutableLiveData<List<ReservationResponseDTO>>()
    val reservations: LiveData<List<ReservationResponseDTO>> get() = _reservations

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadReservations(context: Context) {
        val token = PreferencesRepository.getToken(context)
        if (token != null) {
            viewModelScope.launch {
                ReservationRepository.getReservations(token, context,
                    success = { reservations ->
                        _reservations.value = reservations ?: emptyList()
                    },
                    failure = {
                        _errorMessage.value = it.message
                    }
                )
            }
        } else {
            _errorMessage.value = "No token found"
        }
    }
}
