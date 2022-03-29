package com.waramporn.presentation.mapper

import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.model.City
import com.waramporn.presentation.model.CityLocation
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class DisplayMapperTest {

    @InjectMocks
    lateinit var mapper: DisplayMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun transform() {
        // given
        val cities = listOf(
            City("A", "AA", 0, CityLocation(0.0000, 0.0000)),
            City("B", "BB", 1, CityLocation(1.0000, 1.0000)),
            City("C", "CC", 2, CityLocation(2.0000, 2.0000)),
            City("D", "DD", 3, CityLocation(3.0000, 3.0000)),
        )

        val expect = listOf(
            CityDisplay("AA, A", 0, 0.0000, 0.0000),
            CityDisplay("BB, B", 1, 1.0000, 1.0000),
            CityDisplay("CC, C", 2, 2.0000, 2.0000),
            CityDisplay("DD, D", 3, 3.0000, 3.0000),
        )
        // when
        val actual = mapper.transformList(cities)

        // then
        Assert.assertEquals(expect.size, actual.size)
        actual.zip(expect).forEach {
            Assert.assertEquals(it.first.cityName, it.second.cityName)
            Assert.assertEquals(it.first._id, it.second._id)
            Assert.assertEquals(it.first.lat, it.second.lat, 0.0000)
            Assert.assertEquals(it.first.lon, it.second.lon, 0.0000)
        }
    }
}
