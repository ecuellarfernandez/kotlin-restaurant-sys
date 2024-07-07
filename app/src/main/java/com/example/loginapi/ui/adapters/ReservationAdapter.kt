package com.example.loginapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapi.databinding.ItemReservationBinding
import com.example.loginapi.models.dto.ReservationResponseDTO

class ReservationAdapter(private val reservations: List<ReservationResponseDTO>) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(private val binding: ItemReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: ReservationResponseDTO) {
            binding.tvReservationId.text = "Reservation ID: ${reservation.id}"
            binding.tvReservationDate.text = "Date: ${reservation.date}"
            binding.tvReservationTime.text = "Time: ${reservation.time}"
            binding.tvReservationPeople.text = "People: ${reservation.people}"
            binding.tvReservationFood.text = "Food: " + reservation.food?.joinToString { "${it.qty} x ${it.plate_id}" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReservationBinding.inflate(layoutInflater, parent, false)
        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(reservations[position])
    }

    override fun getItemCount(): Int = reservations.size
}
