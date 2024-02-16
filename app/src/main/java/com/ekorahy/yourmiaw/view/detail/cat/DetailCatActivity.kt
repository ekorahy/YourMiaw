package com.ekorahy.yourmiaw.view.detail.cat

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekorahy.yourmiaw.databinding.ActivityDetailCatBinding
import com.ekorahy.yourmiaw.model.cat.Cat

class DetailCatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cat = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA_CAT, Cat::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA_CAT)
        }

        if (cat != null) {
            binding.ivPhoto.setImageResource(cat.photo)
            binding.tvName.text = cat.name
            binding.tvPrice.text = cat.price.toString()
            binding.tvCategory.text = cat.category
            binding.tvDesc.text = cat.desc
        }
    }

    companion object {
        const val EXTRA_DATA_CAT = "extra_data_cat"
    }
}