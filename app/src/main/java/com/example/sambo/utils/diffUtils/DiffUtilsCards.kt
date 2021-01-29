package com.example.sambo.utils.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.sambo.data.model.cards.RowsItem

object DiffUtilsCards {

    val diffUtilCards = object: DiffUtil.ItemCallback<RowsItem>(){
        override fun areItemsTheSame(oldItem: RowsItem, newItem: RowsItem): Boolean {
            return oldItem.comments_count == newItem.comments_count
                    && oldItem.content  == newItem.content
                    && oldItem.created_at == newItem.created_at
                    && oldItem.description== newItem.description
                    && oldItem.document  == newItem.document
                    && oldItem.id == newItem.id
                    && oldItem.email == newItem.email
                    && oldItem.status_code == newItem.status_code
                    && oldItem.material_type == newItem.material_type
                    && oldItem.preview == newItem.preview
                    && oldItem.rank == newItem.rank
                    && oldItem.title == newItem.title
                    && oldItem.updated_at == newItem.updated_at
        }

        override fun areContentsTheSame(oldItem: RowsItem, newItem: RowsItem): Boolean {
            return oldItem == newItem
        }
    }
}