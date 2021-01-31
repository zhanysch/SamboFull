package com.example.sambo.data.model.modelcourses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Categories (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("rank") val rank : Int,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
):Parcelable