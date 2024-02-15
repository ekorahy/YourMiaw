package com.ekorahy.yourmiaw.model.types

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Types(
    val name: String,
    val photo: Int,
    val desc: String
): Parcelable
