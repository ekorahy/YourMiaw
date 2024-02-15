package com.ekorahy.yourmiaw.view

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekorahy.yourmiaw.model.cat.Cat
import com.ekorahy.yourmiaw.R
import com.ekorahy.yourmiaw.model.types.Types
import com.ekorahy.yourmiaw.adapter.ListPopularAdapter
import com.ekorahy.yourmiaw.adapter.ListRecommendedAdapter
import com.ekorahy.yourmiaw.adapter.ListTypesAdapter
import com.ekorahy.yourmiaw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listTypes = ArrayList<Types>()
    private val listPopular = ArrayList<Cat>()
    private val listRecommended = ArrayList<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTypes.setHasFixedSize(true)
        binding.rvPopular.setHasFixedSize(true)
        binding.rvRecommended.setHasFixedSize(true)

        listTypes.addAll(getListTypes())
        listPopular.addAll(getListPopular())
        listRecommended.addAll(getListRecommended())

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.cvBanner.layoutParams.height = 500
        }

        showList()
    }

    @SuppressLint("Recycle")
    private fun getListRecommended(): ArrayList<Cat> {
        val recommendedName = resources.getStringArray(R.array.recommended_name)
        val recommendedPhoto = resources.obtainTypedArray(R.array.recommended_photo)
        val recommendedRating = resources.getStringArray(R.array.recommended_rating)
        val listRecommended = ArrayList<Cat>()

        for (i in recommendedName.indices) {
            val recommended = Cat(recommendedName[i], recommendedPhoto.getResourceId(i, -1), recommendedRating[i].toDouble())
            listRecommended.add(recommended)
        }
        return listRecommended
    }

    @SuppressLint("Recycle")
    private fun getListPopular(): ArrayList<Cat> {
        val popularName = resources.getStringArray(R.array.popular_name)
        val popularPhoto = resources.obtainTypedArray(R.array.popular_photo)
        val popularRating = resources.getStringArray(R.array.popular_rating)
        val listPopular = ArrayList<Cat>()

        for (i in popularName.indices) {
            val popular = Cat(popularName[i], popularPhoto.getResourceId(i, -1), popularRating[i].toDouble())
            listPopular.add(popular)
        }
        return listPopular
    }

    @SuppressLint("Recycle")
    private fun getListTypes(): ArrayList<Types> {
        val typesName = resources.getStringArray(R.array.types_name)
        val typesPhoto = resources.obtainTypedArray(R.array.types_photo)
        val typesDesc = resources.getStringArray(R.array.types_desc)
        val listTypes = ArrayList<Types>()

        for (i in typesName.indices) {
            val types = Types(typesName[i], typesPhoto.getResourceId(i, -1), typesDesc[i])
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

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvRecommended.layoutManager = GridLayoutManager(this, 4)
        } else {
            binding.rvRecommended.layoutManager = GridLayoutManager(this, 2)
        }

        val listRecommendedAdapter = ListRecommendedAdapter(listRecommended)
        binding.rvRecommended.adapter = listRecommendedAdapter
    }
}