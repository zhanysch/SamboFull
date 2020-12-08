package com.example.sambo.ui.bottomnavigation.adapterForCourseFragment

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter {

}

/*class DiffUtilCalback : DiffUtil.ItemCallback<*//*CinemaSearchItemModel*//* >(){
    override fun areItemsTheSame(oldItem: *//*CinemaSearchItemModel*//* , newItem: *//*CinemaSearchItemModel *//*): Boolean {
        return  oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: CinemaSearchItemModel, newItem: *//*CinemaSearchItemModel*//* ): Boolean {
        return oldItem.id == newItem.id
                && oldItem.title  == newItem.title
                && oldItem.year == newItem.year
                && oldItem.poster  == newItem.poster
    }

}*/

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(){

    }
}