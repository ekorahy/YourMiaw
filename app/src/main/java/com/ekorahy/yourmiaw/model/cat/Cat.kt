package com.ekorahy.yourmiaw.model.cat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val name: String,
    val photo: Int,
    val rating: Double
): Parcelable
