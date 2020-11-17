package com.example.sambo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnBoardModel(
    val title: String,
    val image: Int
) : Parcelable