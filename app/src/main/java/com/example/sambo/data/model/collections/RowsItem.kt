package com.example.sambo.data.model.collections

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RowsItem(
    val created_at: String,
    val icon: String,
    val preview: String,
    val content: String?,
    val id: Int,
    val rank: Int,
    val title: String,
    val updated_at: String
) : Parcelable