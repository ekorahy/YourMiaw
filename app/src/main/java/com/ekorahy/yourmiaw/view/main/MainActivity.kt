package com.ekorahy.yourmiaw.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekorahy.yourmiaw.model.cat.Cat
import com.ekorahy.yourmiaw.R
import com.ekorahy.yourmiaw.model.types.Types
import com.ekorahy.yourmiaw.adapter.ListRecommendedAdapter
import com.ekorahy.yourmiaw.adapter.ListAllMiawAdapter
import com.ekorahy.yourmiaw.adapter.ListTypesAdapter
import com.ekorahy.yourmiaw.databinding.ActivityMainBinding
import com.ekorahy.yourmiaw.view.about.AboutActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listTypes = ArrayList<Types>()
    private val listRecommended = ArrayList<Cat>()
    private val listAllMiaw = ArrayList<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTypes.setHasFixedSize(true)
        binding.rvPopular.setHasFixedSize(true)
        binding.rvRecommended.setHasFixedSize(true)
        binding.aboutPage.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }

        listTypes.addAll(getListTypes())
        listRecommended.addAll(getListRecommended())
        listAllMiaw.addAll(getListAllMiaw())

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.cvBanner.layoutParams.height = 500
        }

        showList()
    }

    @SuppressLint("Recycle")
    private fun getListAllMiaw(): ArrayList<Cat> {
        val miawName = resources.getStringArray(R.array.all_miaw_name)
        val miawPhoto = resources.obtainTypedArray(R.array.all_miaw_photo)
        val miawPrice = resources.getStringArray(R.array.all_miaw_price)
        val miawCategory = resources.getStringArray(R.array.all_miaw_category)
        val miawDesc = resources.getStringArray(R.array.all_miaw_desc)
        val listAllMiau = ArrayList<Cat>()

        for (i in miawName.indices) {
            val miaw = Cat(
                miawName[i],
                miawPhoto.getResourceId(i, -1),
                miawPrice[i].toDouble(),
                miawCategory[i],
                miawDesc[i]
            )
            listAllMiau.add(miaw)
        }
        return listAllMiau
    }

    @SuppressLint("Recycle")
    private fun getListRecommended(): ArrayList<Cat> {
        val recommendedName = resources.getStringArray(R.array.recommended_name)
        val recommendedPhoto = resources.obtainTypedArray(R.array.recommended_photo)
        val recommendedPrice = resources.getStringArray(R.array.recommended_price)
        val recommendedCategory = resources.getStringArray(R.array.recommended_category)
        val recommendedDesc = resources.getStringArray(R.array.recommended_desc)
        val listRecommended = ArrayList<Cat>()

        for (i in recommendedName.indices) {
            val recommended =
                Cat(
                    recommendedName[i],
                    recommendedPhoto.getResourceId(i, -1),
                    recommendedPrice[i].toDouble(),
                    recommendedCategory[i],
                    recommendedDesc[i]
                )
            listRecommended.add(recommended)
        }
        return listRecommended
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
        binding.rvTypes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listTypesAdapter = ListTypesAdapter(listTypes)
        binding.rvTypes.adapter = listTypesAdapter

        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listRecommendedAdapter = ListRecommendedAdapter(listRecommended)
        binding.rvPopular.adapter = listRecommendedAdapter

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvRecommended.layoutManager = GridLayoutManager(this, 4)
        } else {
            binding.rvRecommended.layoutManager = GridLayoutManager(this, 2)
        }

        val listAllMiawAdapter = ListAllMiawAdapter(listAllMiaw)
        binding.rvRecommended.adapter = listAllMiawAdapter
    }
}