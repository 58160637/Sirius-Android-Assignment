package com.waramporn.presentation.mapper

import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.model.City

class DisplayMapper {
    fun transformList(cities: List<City>): List<CityDisplay> {
        return cities
            .sortedBy { it.name }
            .map { transform(it) }
    }

    private fun transform(city: City): CityDisplay {
        return CityDisplay(
            cityName = city.name + ", " + city.country,
            _id = city._id,
            lat = city.coord.lat,
            lon = city.coord.lon
        )
    }
}
