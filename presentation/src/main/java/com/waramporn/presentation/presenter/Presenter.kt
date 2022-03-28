package com.waramporn.presentation.presenter

import android.content.Context
import com.google.gson.Gson
import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.mapper.DisplayMapper
import com.waramporn.presentation.model.City
import com.waramporn.presentation.views.Contractor
import timber.log.Timber
import java.util.Locale

class Presenter(
    private val context: Context,
    private val view: Contractor.View,
    private val mapper: DisplayMapper
) : Contractor.Presenter {

    companion object {
        private const val CITIES_JSON = "cities.json"
        private const val GOOGLE_MAP_URL = "http://maps.google.com/maps?q=loc:%f,%f"
    }

    private lateinit var cityList: List<CityDisplay>
    private var searchCityList = ArrayList<CityDisplay>()

    override fun start() {
        val cityJson = Utils.getJsonDataFromAsset(context, CITIES_JSON)
        try {
            val cities = Gson().fromJson(cityJson, Array<City>::class.java).toList()
            cityList = mapper.transfromList(cities)
            view.showCityList(cityList)
        } catch (ex: Exception) {
            Timber.w(ex)
        }
    }

    override fun search(searchText: String?) {
        if (searchText.isNullOrEmpty()) {
            view.updateList(ArrayList(cityList))
        } else {
            val tampList = ArrayList(cityList)
            searchCityList.clear()
            searchCityList = tampList.filter {
                it.cityName.startsWith(searchText.toString(), false)
            } as ArrayList<CityDisplay>
            if (searchCityList.isNullOrEmpty()) {
                view.showNoResults()
            } else {
                view.updateList(searchCityList)
            }
        }
    }

    override fun onCityClick(lat: Double, lon: Double) {
        val uri: String = String.format(
            Locale.ENGLISH, GOOGLE_MAP_URL, lat, lon
        )
        view.navigateToGoogleMap(uri)
    }
}
