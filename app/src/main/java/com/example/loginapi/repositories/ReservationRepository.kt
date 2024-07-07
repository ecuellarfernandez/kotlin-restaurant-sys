package com.example.loginapi.repositories

import android.content.Context
import com.example.loginapi.api.APIProyecto
import com.example.loginapi.models.dto.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

object ReservationRepository {
    suspend fun addReservation(
        token: String,
        reservation: ReservationRequestDTO,
        context: Context,
        success: (ReservationResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance(context)
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)

        try {
            val response = withContext(Dispatchers.IO) { service.addReservation("Bearer $token", reservation) }
            success(response)
        } catch (t: Throwable) {
            failure(t)
        }
    }

    suspend fun getReservations(
        token: String,
        context: Context,
        success: (List<ReservationResponseDTO>?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance(context)
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)

        try {
            val response = withContext(Dispatchers.IO) { service.getReservations("Bearer $token") }
            success(response)
        } catch (t: Throwable) {
            failure(t)
        }
    }

    suspend fun getReservationById(
        token: String,
        id: Int,
        context: Context,
        success: (ReservationResponseDTO?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance(context)
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)

        try {
            val response = withContext(Dispatchers.IO) { service.getReservationById("Bearer $token", id) }
            success(response)
        } catch (t: Throwable) {
            failure(t)
        }
    }

    suspend fun cancelReservation(
        token: String,
        id: Int,
        context: Context,
        success: (ResponseBody?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance(context)
        val service: APIProyecto = retrofit.create(APIProyecto::class.java)

        try {
            val response = withContext(Dispatchers.IO) { service.cancelReservation("Bearer $token", id) }
            success(response)
        } catch (t: Throwable) {
            failure(t)
        }
    }
}
