package com.example.sambo.data.model.news

import com.example.sambo.data.modelcourses.Categories
import com.google.gson.annotations.SerializedName

data class RowsItem(
    val comments_count: Int,
    val content: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val preview: String,
    val status_code: String,
    val title: String,
    val updated_at: String,
    val material_type: String,
    val description: String,
    val rank: Int,
    val duration: String,
    val categories_list: String,
    val document: String,
    val categories: List<Categories>
)