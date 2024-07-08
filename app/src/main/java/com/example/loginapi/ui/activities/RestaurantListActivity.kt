// src/main/java/com/example/loginapi/ui/activities/RestaurantListActivity.kt
package com.example.loginapi.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapi.databinding.ActivityRestaurantListBinding
import com.example.loginapi.ui.adapters.RestaurantAdapter
import com.example.loginapi.ui.viewmodels.RestaurantViewModel

class RestaurantListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantListBinding
    private lateinit var viewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        binding.rvRestaurants.layoutManager = LinearLayoutManager(this)

        viewModel.restaurants.observe(this, { restaurants ->
            Log.d("RestaurantListActivity", "Observing restaurants: ${restaurants.size}")
            binding.rvRestaurants.adapter = RestaurantAdapter(restaurants) { restaurant ->
                Toast.makeText(this, "Clicked on ${restaurant.name}", Toast.LENGTH_SHORT).show()
                // Aquí puedes iniciar una nueva actividad para mostrar los detalles del restaurante
            }
        })

        viewModel.errorMessage.observe(this, { errorMessage ->
            if (errorMessage != null) {
                Log.e("RestaurantListActivity", "Error message observed: $errorMessage")
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

        Log.d("RestaurantListActivity", "Loading restaurants...")
        viewModel.loadRestaurants()
    }
}
