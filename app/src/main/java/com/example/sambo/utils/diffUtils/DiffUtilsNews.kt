package com.example.sambo.utils.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.sambo.data.model.news.RowsItem


object DiffUtilsNews {

    val diffUtilsNews = object : DiffUtil.ItemCallback<RowsItem>() {
        override fun areItemsTheSame(oldItem: RowsItem, newItem: RowsItem): Boolean {
            return oldItem.comments_count == newItem.comments_count
                    && oldItem.content == newItem.content
                    && oldItem.created_at == newItem.created_at
                    && oldItem.email == newItem.email
                    && oldItem.id == newItem.id
                    && oldItem.preview == newItem.preview
                    && oldItem.status_code == newItem.status_code
                    && oldItem.title == newItem.title
                    && oldItem.updated_at == newItem.updated_at
        }

        override fun areContentsTheSame(oldItem: RowsItem, newItem: RowsItem): Boolean {
            return oldItem == newItem
        }
    }
}