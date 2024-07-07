package com.example.loginapi.repositories

import android.content.Context
import com.example.loginapi.api.APIProyecto
import com.example.loginapi.models.dto.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UserRepository {
    suspend fun doLogin(
        email: String,
        password: String,
        context: Context,
        success: (LoginResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance(context)
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)

        try {
            val response = withContext(Dispatchers.IO) { service.login(LoginRequestDTO(email, password)) }
            if (response.access_token != null) {
                success(response)
            } else {
                success(null)
            }
        } catch (t: Throwable) {
            failure(t)
        }
    }

    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        phone: String,
        context: Context,
        success: (LoginResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance(context)
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)

        try {
            val response = withContext(Dispatchers.IO) { service.registerUser(RegistroRequestDTO(name, email, password, phone)) }
            success(response)
        } catch (t: Throwable) {
            failure(t)
        }
    }
}
