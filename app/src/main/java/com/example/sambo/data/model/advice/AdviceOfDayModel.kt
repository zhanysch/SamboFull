package com.example.sambo.data.model.advice

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AdviceOfDayModel(
    val created_at: String,
    val description: String,
    val name: String,
    val updated_at: String
) : Parcelable