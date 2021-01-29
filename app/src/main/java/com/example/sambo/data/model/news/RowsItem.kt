package com.example.sambo.data.model.news

data class RowsItem(
    val comments_count: Int,
    val content: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val preview: String,
    val status_code: String,
    val title: String,
    val updated_at: String
)