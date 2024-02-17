package com.ekorahy.yourmiaw.view.detail.cat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ekorahy.yourmiaw.R
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
            with(binding) {
                ivPhoto.setImageResource(cat.photo)
                tvName.text = cat.name
                tvPrice.text = cat.price.toString()
                tvCategory.text = cat.category
                tvDesc.text = cat.desc
                btnBack.setOnClickListener {
                    onBackPressedDispatcher.onBackPressed()
                }
                btnBuy.setOnClickListener {
                    Toast.makeText(
                        this@DetailCatActivity,
                        getString(R.string.toast_buy, cat.name),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                actionShare.setOnClickListener {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            getString(
                                R.string.share_content_cat,
                                cat.name,
                                cat.price.toString(),
                                cat.category,
                                cat.desc
                            )
                        )
                    }
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
                }
            }
        }
    }

    companion object {
        const val EXTRA_DATA_CAT = "extra_data_cat"
    }
}