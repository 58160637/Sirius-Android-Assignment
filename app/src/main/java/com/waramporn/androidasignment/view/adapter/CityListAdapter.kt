package com.waramporn.androidasignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waramporn.androidasignment.R
import com.waramporn.presentation.model.City

class CityListAdapter(
    private val cities: List<City>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_city_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val city = itemView.findViewById(R.id.tv_city) as TextView
        val lat = itemView.findViewById(R.id.tv_lat) as TextView
        val lon = itemView.findViewById(R.id.tv_lon) as TextView
    }
}