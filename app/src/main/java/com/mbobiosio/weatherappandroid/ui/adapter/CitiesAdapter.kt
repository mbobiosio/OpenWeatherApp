package com.mbobiosio.weatherappandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.weatherappandroid.databinding.ItemCitiesBinding
import com.mbobiosio.weatherappandroid.listener.CityClickListener
import com.mbobiosio.weatherappandroid.model.City

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
class CitiesAdapter(
    private val listener: CityClickListener
) : ListAdapter<City, CitiesAdapter.CitiesViewHolder>(ItemCallback()) {

    private class ItemCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val binding = ItemCitiesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, listener)
        }
    }

    class CitiesViewHolder(
        private val binding: ItemCitiesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            city: City,
            listener: CityClickListener
        ) = with(itemView) {
            binding.city = city
            binding.clickListener = listener
            binding.executePendingBindings()
        }
    }
}