package com.ekorahy.yourmiaw

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Popular(
    val name: String,
    val photo: Int,
    val rating: Double
): Parcelable
