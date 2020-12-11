package com.example.sambo.data.modelcourses

import com.google.gson.annotations.SerializedName



data class Categories (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("rank") val rank : Int,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
)