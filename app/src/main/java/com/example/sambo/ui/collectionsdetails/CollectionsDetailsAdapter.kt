package com.example.sambo.ui.collectionsdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.utils.diffUtils.DiffUtilsItems
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_collections_details.view.*
import kotlinx.android.synthetic.main.item_collections_details.view.tvDescriptions

class CollectionsDetailsAdapter(private val listener: (item: RowsModel, image: ImageView) -> Unit) :
    ListAdapter<RowsModel, CollectionsDetailsViewHolder>(DiffUtilsItems.diffUtilItems) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionsDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collections_details, parent, false)
        return CollectionsDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionsDetailsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class CollectionsDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: RowsModel?, listener: (item: RowsModel, image: ImageView) -> Unit) {
        itemView.tvDescriptions.text = item?.title.toString()
        Picasso.get()
            .load(item?.preview)
            .placeholder(R.drawable.ic_listing_placeholder)
            .error(R.drawable.ic_listing_placeholder)
            .into(itemView.ivCollectionsDetails)

        itemView.ivCollectionsDetails.transitionName =
            itemView.context.resources.getString(R.string.image_transition, item?.id)

        itemView.collectionsItem.setOnClickListener {
            item?.let { it1 -> listener.invoke(it1, itemView.ivCollectionsDetails) }
        }
    }
}
