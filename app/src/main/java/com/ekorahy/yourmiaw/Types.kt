package com.ekorahy.yourmiaw

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Types(
    val name: String,
    val photo: Int
): Parcelable
