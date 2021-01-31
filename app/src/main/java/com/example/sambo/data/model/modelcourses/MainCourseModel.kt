package com.example.sambo.data.model.modelcourses

import com.google.gson.annotations.SerializedName



data class MainCourseModel<T>(

	@SerializedName("rows") val rows : List<T>,
	@SerializedName("total_count") val total_count : Int
)