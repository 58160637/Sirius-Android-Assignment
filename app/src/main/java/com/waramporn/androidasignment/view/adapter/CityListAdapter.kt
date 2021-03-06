package com.waramporn.androidasignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.waramporn.androidasignment.R
import com.waramporn.presentation.display.CityDisplay

class CityListAdapter(
    private val cities: ArrayList<CityDisplay>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_city_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position], listener)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun updateList(cityList: List<CityDisplay>) {
        cities.clear()
        cities.addAll(cityList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_city)
        lateinit var tvCity: TextView

        @BindView(R.id.tv_lat)
        lateinit var tvLat: TextView

        @BindView(R.id.tv_lon)
        lateinit var tvLon: TextView

        @BindView(R.id.card_view)
        lateinit var cityCardView: CardView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(
            item: CityDisplay,
            onClickListener: OnClickListener
        ) {
            tvCity.text = item.cityName
            tvLat.text = item.lat.toString()
            tvLon.text = item.lon.toString()
            cityCardView.setOnClickListener { onClickListener.onClick(item.lat, item.lon) }
        }
    }

    interface OnClickListener {
        fun onClick(lat: Double, lon: Double)
    }
}
