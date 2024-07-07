package com.example.loginapi.models.dto

data class LoginRequestDTO(
    var email: String,
    var password: String
)

data class LoginResponseDTO(
    var access_token: String? = null
)

data class Restaurant(
    val name: String,
    val address: String = "Av 123",
    val city: String,
    val description: String = "venta de comida"
)

data class RegistroRequestDTO(
    var name: String,
    var email: String,
    var password: String,
    var phone: String
)

data class SearchRequestDTO(
    var name: String
)

data class ReservationRequestDTO(
    val restaurant_id: Int,
    val date: String,
    val time: String,
    val people: Int,
    val food: List<FoodItem>? = null
)

data class ReservationResponseDTO(
    val id: Int,
    val restaurant_id: Int,
    val date: String,
    val time: String,
    val people: Int,
    val food: List<FoodItem>?
)

data class FoodItem(
    val plate_id: Int,
    val qty: Int
)