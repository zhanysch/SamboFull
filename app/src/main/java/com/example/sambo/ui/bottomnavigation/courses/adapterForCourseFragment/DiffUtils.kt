package com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment

import com.example.sambo.data.modelcourses.Rows
import androidx.recyclerview.widget.DiffUtil

object DiffUtils {
    val diffUtilDAta = object: DiffUtil.ItemCallback<Rows>(){
        override fun areItemsTheSame(oldItem: Rows, newItem: Rows): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title  == newItem.title
                    && oldItem.description == newItem.description
                    && oldItem.rank== newItem.rank
                    && oldItem.created_at  == newItem.created_at
                    && oldItem.updated_at == newItem.updated_at
        }

        override fun areContentsTheSame(oldItem: Rows, newItem: Rows): Boolean {
            return oldItem == newItem
        }

    }
}