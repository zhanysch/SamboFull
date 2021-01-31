package com.example.sambo.ui.bottomnavigation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sambo.R
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.utils.diffUtils.DiffUtilsCards
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main_cards.view.*

class CardsAdapter(private val listener: (item: RowsItem, image: ImageView) -> Unit) :
    ListAdapter<RowsItem, CardsViewHolder>(DiffUtilsCards.diffUtilCards) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_cards, parent, false)
        return CardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class CardsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: RowsItem?, listener: (item: RowsItem, image: ImageView) -> Unit) {
        itemView.tvDescriptions.text = item?.title.toString()
        Picasso.get()
            .load(item?.preview)
            .placeholder(R.drawable.ic_listing_placeholder)
            .error(R.drawable.ic_listing_placeholder)
            .into(itemView.ivCards)

        itemView.ivCards.transitionName =
            itemView.context.resources.getString(R.string.image_transition, item?.id)

        itemView.mainCard.setOnClickListener {
            item?.let { it1 -> listener.invoke(it1, itemView.ivCards) }
        }
    }
}