package com.example.sambo.ui.collectionsdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.utils.diffUtils.DiffUtilsItems
import com.example.sambo.utils.ext.setCornerRadius
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main_collections.view.*

class CollectionsAdapter(private val listener: (item: RowsModel) -> Unit) :
    ListAdapter<RowsModel, CollectionsViewHolder>(DiffUtilsItems.diffUtilItems) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_collections, parent, false)
        return CollectionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class CollectionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: RowsModel?, listener: (item: RowsModel) -> Unit) {
        itemView.tvDescriptions.text = item?.title.toString()
        itemView.collectionsCard.setOnClickListener {
            item?.let { it1 -> listener.invoke(it1) }
        }
        Picasso.get()
            .load(item?.icon)
            .placeholder(R.drawable.ic_listing_placeholder)
            .error(R.drawable.ic_listing_placeholder)
            .into(itemView.ivCollections)

        val radius = itemView.resources.getDimension(R.dimen.dp32)
        itemView.ivCollections.setCornerRadius(
            topLeft = radius,
            topRight = radius,
            bottomLeft = radius,
            bottomRight = radius
        )
    }
}