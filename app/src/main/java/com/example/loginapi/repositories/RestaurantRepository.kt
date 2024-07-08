// src/main/java/com/example/loginapi/repositories/RestaurantRepository.kt
package com.example.loginapi.repositories

import com.example.loginapi.api.APIProyecto
import com.example.loginapi.models.dto.Restaurant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestaurantRepository {
    private val api: APIProyecto

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restaurantes.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(APIProyecto::class.java)
    }

    suspend fun getRestaurants(): List<Restaurant> {
        return api.getRestaurants()
    }
}
