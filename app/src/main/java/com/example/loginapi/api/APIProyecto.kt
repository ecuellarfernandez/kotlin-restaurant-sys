package com.example.loginapi.api

import com.example.loginapi.models.dto.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface APIProyecto {
    @POST("loginuser")
    suspend fun login(@Body loginRequest: LoginRequestDTO): LoginResponseDTO

    @POST("registeruser")
    suspend fun registerUser(@Body registroRequest: RegistroRequestDTO): LoginResponseDTO

    @POST("restaurants")
    suspend fun insertRestaurant(@Header("Authorization") token: String, @Body restaurant: Restaurant): Restaurant

    @GET("restaurants")
    suspend fun getRestaurants(): List<Restaurant>

    @POST("restaurants/search")
    suspend fun searchRestaurants(@Body searchRequest: SearchRequestDTO): List<Restaurant>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Header("Authorization") token: String, @Path("id") id: Int): Restaurant

    @POST("restaurants/{id}/photo")
    suspend fun uploadPhoto(@Header("Authorization") token: String, @Path("id") id: Int, @Body photo: MultipartBody.Part): ResponseBody

    @POST("restaurants/{id}/logo")
    suspend fun uploadLogo(@Header("Authorization") token: String, @Path("id") id: Int, @Body logo: MultipartBody.Part): ResponseBody

    @PUT("restaurants/{id}")
    suspend fun updateRestaurant(@Header("Authorization") token: String, @Path("id") id: Int, @Body restaurant: Restaurant): Restaurant

    @PATCH("restaurants/{id}")
    suspend fun patchRestaurant(@Header("Authorization") token: String, @Path("id") id: Int, @Body restaurant: Restaurant): Restaurant

    @DELETE("restaurants/{id}")
    suspend fun deleteRestaurant(@Header("Authorization") token: String, @Path("id") id: Int): ResponseBody

    @POST("reservations")
    suspend fun addReservation(@Header("Authorization") token: String, @Body reservation: ReservationRequestDTO): ReservationResponseDTO

    @GET("reservations")
    suspend fun getReservations(@Header("Authorization") token: String): List<ReservationResponseDTO>

    @GET("reservations/{id}")
    suspend fun getReservationById(@Header("Authorization") token: String, @Path("id") id: Int): ReservationResponseDTO

    @POST("reservations/{id}/cancel")
    suspend fun cancelReservation(@Header("Authorization") token: String, @Path("id") id: Int): ResponseBody
}
