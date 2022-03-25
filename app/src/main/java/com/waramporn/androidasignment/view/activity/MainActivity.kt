package com.waramporn.androidasignment.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.waramporn.androidasignment.R
import com.waramporn.androidasignment.view.adapter.CityListAdapter
import com.waramporn.presentation.model.City
import com.waramporn.presentation.model.CityLocation
import com.waramporn.presentation.presenter.Presenter
import com.waramporn.presentation.views.Contractor


class MainActivity : AppCompatActivity(), Contractor.View, CityListAdapter.OnClickListener {

    @BindView(R.id.rv_city)
    lateinit var rvCity: RecyclerView

    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        presenter = Presenter(this, this)
        presenter.start()
    }

    override fun showCityList(cities: List<City>) {
        rvCity.layoutManager = LinearLayoutManager(this)
        val cityListAdapter = CityListAdapter(cities, this)
        rvCity.adapter = cityListAdapter

    }

    override fun onClick(location: CityLocation) {
        Log.d("location", "click")
    }
}
