package com.waramporn.presentation.presenter

import android.content.Context
import com.google.gson.Gson
import com.waramporn.presentation.model.City
import com.waramporn.presentation.views.Contractor
import timber.log.Timber

class Presenter(
    private val context: Context,
    val view: Contractor.View
) : Contractor.Presenter {

    companion object {
        private const val CITIES_JSON = "cities.json"
    }

    lateinit var cities: List<City>

    override fun start() {
        val cityJson = Utils.getJsonDataFromAsset(context, CITIES_JSON)
        try {
            cities = Gson().fromJson(cityJson, Array<City>::class.java).toList()
            view.showCityList(cities)
        } catch (ex: Exception) {
            Timber.w(ex)
        }
    }

}
