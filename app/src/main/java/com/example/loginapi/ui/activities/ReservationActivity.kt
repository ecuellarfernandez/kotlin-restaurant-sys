package com.example.loginapi.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapi.databinding.ActivityReservationsBinding
import com.example.loginapi.ui.adapters.ReservationAdapter
import com.example.loginapi.ui.viewmodels.ReservationViewModel

class ReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationsBinding
    private val viewModel: ReservationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvReservations.layoutManager = LinearLayoutManager(this)
        viewModel.reservations.observe(this, Observer { reservations ->
            binding.rvReservations.adapter = ReservationAdapter(reservations)
        })

        viewModel.loadReservations(this)

        viewModel.errorMessage.observe(this, Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
