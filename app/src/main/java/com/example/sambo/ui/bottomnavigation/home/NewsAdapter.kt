package com.example.sambo.ui.bottomnavigation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.utils.diffUtils.DiffUtilsCards
import com.example.sambo.utils.ext.setCornerRadius
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main_news.view.*


class NewsAdapter(private val listener: (item: RowsItem) -> Unit): ListAdapter<RowsItem, NewsViewHolder>(DiffUtilsCards.diffUtilCards) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_news,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
    fun bind(item: RowsItem?, listener: (item: RowsItem) -> Unit) {
        itemView.tvNews.text = item?.title.toString()
        itemView.newsItem.setOnClickListener {
            item?.let { it1 -> listener.invoke(it1) }
        }
        Picasso.get()
            .load(item?.preview)
            .placeholder(R.drawable.ic_listing_placeholder)
            .error(R.drawable.ic_listing_placeholder)
            .into(itemView.ivNews)

        val radius = itemView.resources.getDimension(R.dimen.imageNewsRadius)
        itemView.ivNews.setCornerRadius(
            topLeft = radius,
            topRight = radius,
            bottomLeft = radius,
            bottomRight = radius
        )
    }
}