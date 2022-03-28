package com.waramporn.presentation.presenter

import android.content.Context
import com.google.gson.Gson
import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.mapper.DisplayMapper
import com.waramporn.presentation.model.City
import com.waramporn.presentation.views.Contractor
import timber.log.Timber

class Presenter(
    private val context: Context,
    val view: Contractor.View,
    val mapper: DisplayMapper
) : Contractor.Presenter {

    companion object {
        private const val CITIES_JSON = "cities.json"
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
        if (searchText?.isEmpty() == true) {
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
}
