package com.ekorahy.yourmiaw.view.detail.category

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekorahy.yourmiaw.R
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
            with(binding) {
                ivPhoto.setImageResource(category.photo)
                tvName.text = category.name
                tvDesc.text = category.desc
                btnBack.setOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
                actionShare.setOnClickListener {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            getString(R.string.share_content_category, category.name, category.desc)
                        )
                    }
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
                }
            }
        }
    }

    companion object {
        const val EXTRA_DATA_CATEGORY = "extra_data_category"
    }
}