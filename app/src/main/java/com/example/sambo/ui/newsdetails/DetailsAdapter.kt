package com.example.sambo.ui.newsdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.utils.diffUtils.DiffUtilsItems
import com.example.sambo.utils.ext.setCornerRadius
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main_news.view.*

class DetailsAdapter(private val listener: (item: RowsModel, image: ShapeableImageView) -> Unit) :
    ListAdapter<RowsModel, NewsViewHolder>(DiffUtilsItems.diffUtilItems) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: RowsModel?, listener: (item: RowsModel, image: ShapeableImageView) -> Unit) {
        itemView.tvNews.text = item?.title.toString()
        Picasso.get()
            .load(item?.preview)
            .placeholder(R.drawable.ic_listing_placeholder)
            .error(R.drawable.ic_listing_placeholder)
            .into(itemView.ivNews)

        itemView.ivNews.transitionName =
            itemView.context.resources.getString(R.string.image_transition, item?.id)

        val radius = itemView.resources.getDimension(R.dimen.imageNewsRadius)
        itemView.ivNews.setCornerRadius(
            topLeft = radius,
            topRight = radius,
            bottomLeft = radius,
            bottomRight = radius
        )

        itemView.newsItem.setOnClickListener {
            item?.let { it1 -> listener.invoke(it1, itemView.ivNews) }
        }
    }
}