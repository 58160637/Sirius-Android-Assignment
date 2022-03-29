package com.waramporn.presentation.presenter

import android.content.Context
import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.mapper.DisplayMapper
import com.waramporn.presentation.views.Contractor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class PresenterTest {
    @Mock
    lateinit var view: Contractor.View

    @Mock
    lateinit var mapper: DisplayMapper

    @Mock
    lateinit var context: Context

    @InjectMocks
    lateinit var presenter: Presenter

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test 'A' prefix get all city with A first`() {
        // given
        presenter.cityList = listOf(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
            mock { on(it.cityName).thenReturn("Sydney, AU") },
        )
        val expectResult = listOf<CityDisplay>(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
        )

        // when
        presenter.search("A")

        // then
        Assert.assertEquals(expectResult.size, presenter.searchCityList.size)
        expectResult.zip(presenter.searchCityList).forEach {
            Assert.assertEquals(it.first.cityName, it.second.cityName)
        }
        verify(view).updateList(any())
    }

    @Test
    fun `test lowercase 's' prefix get one city with s or S first`() {
        // given
        presenter.cityList = listOf(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
            mock { on(it.cityName).thenReturn("Sydney, AU") },
        )
        val expectResult = listOf<CityDisplay>(
            mock { on(it.cityName).thenReturn("Sydney, AU") }
        )

        // when
        presenter.search("s")

        // then
        Assert.assertEquals(expectResult.size, presenter.searchCityList.size)
        expectResult.zip(presenter.searchCityList).forEach {
            Assert.assertEquals(it.first.cityName, it.second.cityName)
        }
        verify(view).updateList(any())
    }

    @Test
    fun `test 'Al' prefix get all city with 'Al' first`() {
        // given
        presenter.cityList = listOf(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
            mock { on(it.cityName).thenReturn("Sydney, AU") },
        )
        val expectResult = listOf<CityDisplay>(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") }
        )

        // when
        presenter.search("Al")

        // then
        Assert.assertEquals(expectResult.size, presenter.searchCityList.size)
        expectResult.zip(presenter.searchCityList).forEach {
            Assert.assertEquals(it.first.cityName, it.second.cityName)
        }
        verify(view).updateList(any())
    }

    @Test
    fun `test 'Alb' prefix get all city with 'Alb' first`() {
        // given
        presenter.cityList = listOf(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
            mock { on(it.cityName).thenReturn("Sydney, AU") },
        )
        val expectResult = listOf<CityDisplay>(
            mock { on(it.cityName).thenReturn("Albuquerque, US") }
        )

        // when
        presenter.search("Alb")

        // then
        Assert.assertEquals(expectResult.size, presenter.searchCityList.size)
        expectResult.zip(presenter.searchCityList).forEach {
            Assert.assertEquals(it.first.cityName, it.second.cityName)
        }
        verify(view).updateList(any())
    }

    @Test
    fun `test search not found`() {
        // given
        presenter.cityList = listOf(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
            mock { on(it.cityName).thenReturn("Sydney, AU") },
        )

        // when
        presenter.search("665")

        // then
        Assert.assertEquals(0, presenter.searchCityList.size)
        verify(view).showNoResults()
    }

    @Test
    fun `test null search reset list`() {
        // given
        val expect = listOf<CityDisplay>(
            mock { on(it.cityName).thenReturn("Alabama, US") },
            mock { on(it.cityName).thenReturn("Albuquerque, US") },
            mock { on(it.cityName).thenReturn("Anaheim, US") },
            mock { on(it.cityName).thenReturn("Arizona, US") },
            mock { on(it.cityName).thenReturn("Sydney, AU") },
        )
        presenter.cityList = expect

        // when
        presenter.search(null)

        // then
        verify(view).updateList(ArrayList(expect))
    }

    @Test
    fun `test onCityClick`() {
        // given
        val expect = "http://maps.google.com/maps?q=loc:123.000000,123.000000"

        // when
        presenter.onCityClick(123.000000, 123.000000)

        // then
        verify(view).navigateToGoogleMap(expect)
    }
}
