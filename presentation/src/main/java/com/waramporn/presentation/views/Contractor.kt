package com.waramporn.presentation.views

import com.waramporn.presentation.display.CityDisplay

interface Contractor {
    interface View {
        fun showCityList(cities: List<CityDisplay>)
        fun updateList(cityList: ArrayList<CityDisplay>)
        fun showNoResults()
        fun navigateToGoogleMap(uri: String)
    }

    interface Presenter {
        fun start()
        fun search(searchText: String?)
        fun onCityClick(lat: Double, lon: Double)
    }
}
