package com.waramporn.androidasignment.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.waramporn.androidasignment.R
import com.waramporn.androidasignment.view.adapter.CityListAdapter
import com.waramporn.presentation.display.CityDisplay
import com.waramporn.presentation.mapper.DisplayMapper
import com.waramporn.presentation.presenter.Presenter
import com.waramporn.presentation.views.Contractor

class MainActivity : AppCompatActivity(), Contractor.View, CityListAdapter.OnClickListener {

    @BindView(R.id.rv_city)
    lateinit var rvCity: RecyclerView

    @BindView(R.id.tv_no_results)
    lateinit var tvResults: TextView

    lateinit var presenter: Presenter
    lateinit var mapper: DisplayMapper
    lateinit var cityListAdapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        mapper = DisplayMapper()
        presenter = Presenter(this, this, mapper)
        presenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu?.findItem(R.id.item_search)
        val etSearch = item?.actionView as SearchView
        etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.search(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun showCityList(cities: List<CityDisplay>) {
        rvCity.layoutManager = LinearLayoutManager(this)
        cityListAdapter = CityListAdapter(ArrayList(cities), this)
        rvCity.adapter = cityListAdapter
    }

    override fun updateList(cityList: ArrayList<CityDisplay>) {
        hideNoResults()
        cityListAdapter.updateList(cityList)
    }

    override fun showNoResults() {
        rvCity.visibility = View.GONE
        tvResults.visibility = View.VISIBLE
    }

    override fun hideNoResults() {
        tvResults.visibility = View.GONE
        rvCity.visibility = View.VISIBLE
    }

    override fun navigateToGoogleMap(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    override fun onClick(lat: Double, lon: Double) {
        presenter.onCityClick(lat, lon)
    }
}
