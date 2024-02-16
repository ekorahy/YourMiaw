package com.ekorahy.yourmiaw.view.detail.category

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekorahy.yourmiaw.databinding.ActivityDetailCategoryBinding
import com.ekorahy.yourmiaw.model.types.Types

class DetailCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA_CATEGORY, Types::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA_CATEGORY)
        }

        if (category != null) {
            binding.ivPhoto.setImageResource(category.photo)
            binding.tvName.text = category.name
            binding.tvDesc.text = category.desc
        }
    }

    companion object {
        const val EXTRA_DATA_CATEGORY = "extra_data_category"
    }
}