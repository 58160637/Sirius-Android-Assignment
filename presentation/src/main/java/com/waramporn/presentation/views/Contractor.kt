package com.waramporn.presentation.views

import com.waramporn.presentation.model.City

interface Contractor {
    interface View {
        fun showCityList(cities: List<City>)
    }

    interface Presenter {
        fun start()
    }
}
