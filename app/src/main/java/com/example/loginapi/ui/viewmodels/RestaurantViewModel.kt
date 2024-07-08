// src/main/java/com/example/loginapi/ui/viewmodels/RestaurantViewModel.kt
package com.example.loginapi.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapi.models.dto.Restaurant
import com.example.loginapi.repositories.RestaurantRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RestaurantViewModel : ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadRestaurants() {
        viewModelScope.launch {
            try {
                Log.d("RestaurantViewModel", "Loading restaurants...")
                val restaurants = RestaurantRepository.getRestaurants()
                _restaurants.value = restaurants
                Log.d("RestaurantViewModel", "Restaurants loaded: ${restaurants.size}")
            } catch (e: HttpException) {
                _errorMessage.value = "Error: ${e.message()}"
                Log.e("RestaurantViewModel", "HTTP Exception: ${e.message()}")
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
                Log.e("RestaurantViewModel", "Exception: ${e.localizedMessage}")
            }
        }
    }
}
