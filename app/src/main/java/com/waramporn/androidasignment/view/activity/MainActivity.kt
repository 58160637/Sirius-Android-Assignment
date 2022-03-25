package com.waramporn.androidasignment.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.waramporn.androidasignment.R
import com.waramporn.androidasignment.view.adapter.CityListAdapter
import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.mapper.DisplayMapper
import com.waramporn.presentation.presenter.Presenter
import com.waramporn.presentation.views.Contractor


class MainActivity : AppCompatActivity(), Contractor.View, CityListAdapter.OnClickListener {

    @BindView(R.id.rv_city)
    lateinit var rvCity: RecyclerView

    lateinit var presenter: Presenter
    lateinit var mapper: DisplayMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        mapper = DisplayMapper()
        presenter = Presenter(this, this, mapper)
        presenter.start()
    }

    override fun showCityList(cities: List<CityDisplay>) {
        rvCity.layoutManager = LinearLayoutManager(this)
        val cityListAdapter = CityListAdapter(cities, this)
        rvCity.adapter = cityListAdapter

    }

    override fun onClick(lat: Double, lon: Double) {
        Toast.makeText(this, "lat: " + lat + " \nlon : " + lon, Toast.LENGTH_SHORT).show()
    }
}
