package com.example.sambo.data.model.listing

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesModel(
    val id: Int,
    val title: String,
    val rank: Int,
    val created_at: String,
    val updated_at: String
) : Parcelable