package com.mbobiosio.weatherappandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
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
) : RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    private var dataList: List<City> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val binding = ItemCitiesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CitiesViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) =
        holder.bind(dataList[position], listener)

    fun setData(cities: List<City>) {
        this.dataList = cities
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