package com.waramporn.presentation.views

import com.waramporn.presentation.display.CityDisplay

interface Contractor {
    interface View {
        fun showCityList(cities: List<CityDisplay>)
    }

    interface Presenter {
        fun start()
    }
}
