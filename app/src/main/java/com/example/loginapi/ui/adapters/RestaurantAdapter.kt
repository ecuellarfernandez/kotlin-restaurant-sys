// src/main/java/com/example/loginapi/ui/adapters/RestaurantAdapter.kt
package com.example.loginapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapi.databinding.ItemRestaurantBinding
import com.example.loginapi.models.dto.Restaurant

class RestaurantAdapter(private val restaurants: List<Restaurant>, private val onClick: (Restaurant) -> Unit) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant, onClick: (Restaurant) -> Unit) {
            binding.tvRestaurantName.text = restaurant.name
            binding.tvRestaurantCity.text = restaurant.city
            // Aquí se puede agregar la carga de la imagen del logo usando una librería como Glide o Picasso
            binding.root.setOnClickListener { onClick(restaurant) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRestaurantBinding.inflate(layoutInflater, parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position], onClick)
    }

    override fun getItemCount(): Int = restaurants.size
}
