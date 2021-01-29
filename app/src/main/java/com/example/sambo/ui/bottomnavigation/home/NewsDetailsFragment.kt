package com.example.sambo.ui.bottomnavigation.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.commonpagination.BaseFragment
import com.example.sambo.utils.ext.setCornerRadius
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_details.*


class NewsDetailsFragment : BaseFragment() {
    override fun resID() = R.layout.fragment_news_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupViews() {
        val bundle = NewsDetailsFragmentArgs.fromBundle(requireArguments())

        tvTitle.text = bundle.details?.title
        tvDescription.text = bundle.details?.content
        Picasso.get()
            .load(bundle.details?.preview)
            .placeholder(R.drawable.ic_listing_placeholder)
            .error(R.drawable.ic_listing_placeholder)
            .into(ivNewsDetails)

        val imageRadius = resources.getDimension(R.dimen.dp20)
        ivNewsDetails.setCornerRadius(
            bottomLeft = imageRadius,
            bottomRight = imageRadius
        )
    }
}