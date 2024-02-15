package com.ekorahy.yourmiaw

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.ekorahy.yourmiaw.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listTypes = ArrayList<Types>()
    private val listPopular = ArrayList<Popular>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTypes.setHasFixedSize(true)
        binding.rvPopular.setHasFixedSize(true)

        listTypes.addAll(getListTypes())
        listPopular.addAll(getListPopular())
        showList()
    }

    @SuppressLint("Recycle")
    private fun getListPopular(): ArrayList<Popular> {
        val popularName = resources.getStringArray(R.array.popular_name)
        val popularPhoto = resources.obtainTypedArray(R.array.popular_photo)
        val popularRating = resources.getStringArray(R.array.popular_rating)
        val listPopular = ArrayList<Popular>()

        for (i in popularName.indices) {
            val popular = Popular(popularName[i], popularPhoto.getResourceId(i, -1), popularRating[i].toDouble())
            listPopular.add(popular)
        }
        return listPopular
    }

    @SuppressLint("Recycle")
    private fun getListTypes(): ArrayList<Types> {
        val typesName = resources.getStringArray(R.array.types_name)
        val typesPhoto = resources.obtainTypedArray(R.array.types_photo)
        val listTypes = ArrayList<Types>()

        for (i in typesName.indices) {
            val types = Types(typesName[i], typesPhoto.getResourceId(i, -1))
            listTypes.add(types)
        }
        return listTypes
    }

    private fun showList() {
        binding.rvTypes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listTypesAdapter = ListTypesAdapter(listTypes)
        binding.rvTypes.adapter = listTypesAdapter

        binding.rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listPopularAdapter = ListPopularAdapter(listPopular)
        binding.rvPopular.adapter = listPopularAdapter
    }
}