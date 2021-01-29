package com.example.sambo.data.model.news

import com.example.sambo.data.model.cards.RowsItem

data class NewsModel(
    val rows: List<RowsItem>,
    val total_count: Int
)