package com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment

import android.util.Log
import com.example.sambo.data.modelcourses.Rows
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_helper.view.*
import java.lang.Exception

class CoursesAdapter: PagedListAdapter<Rows,CoursesViewHolder>(DiffUtils.diffUtilDAta) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_helper,parent,false)
        return CoursesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CoursesViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bind(item: Rows?) {
        itemView.textCourse.text = item?.title.toString()
        itemView.Material.text = item?.categories_list.toString()
        Picasso.get().load(item?.preview).into(itemView.imageCourse)
    }
}