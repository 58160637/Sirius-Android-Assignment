package com.waramporn.androidasignment.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.waramporn.androidasignment.R
import com.waramporn.presentation.model.City
import com.waramporn.presentation.presenter.Presenter
import com.waramporn.presentation.views.Contractor

class MainActivity : AppCompatActivity(), Contractor.View {

    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this,this)
        presenter.start()
    }

    override fun showCityList(cities: List<City>) {

    }
}