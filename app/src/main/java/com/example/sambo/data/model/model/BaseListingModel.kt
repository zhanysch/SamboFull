package com.example.sambo.data.model.model

data class BaseListingModel<T>(
    val rows: List<T>,
    val total_count: Int
)