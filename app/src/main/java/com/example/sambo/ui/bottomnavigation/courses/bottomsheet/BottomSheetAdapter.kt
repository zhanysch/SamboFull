package com.example.sambo.ui.bottomnavigation.courses.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import kotlinx.android.synthetic.main.bottom_sheethelper.view.*

class BottomSheetAdapter(private val listner: ItemListener) :
    ListAdapter<BottomSheetRows, BottomViewHolder>(DiffUtillBottom()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheethelper, parent, false)
        return BottomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.category_bottomsheet.setOnClickListener {
            listner.itemsClick(getItem(position))       // для изминения textview course fragment при клике textview из recyclrerVIew!!!!!!!!
        }
    }
}

class DiffUtillBottom : DiffUtil.ItemCallback<BottomSheetRows>() {
    override fun areItemsTheSame(oldItem: BottomSheetRows, newItem: BottomSheetRows): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: BottomSheetRows, newItem: BottomSheetRows): Boolean {
        return oldItem.title == newItem.title
    }
}

class BottomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        item: BottomSheetRows

    ) {
        itemView.category_bottomsheet.text = item.title
    }
}