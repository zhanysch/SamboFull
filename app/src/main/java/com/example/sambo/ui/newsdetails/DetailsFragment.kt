package com.example.sambo.ui.newsdetails

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sambo.R
import com.example.sambo.data.common.BaseFragment
import com.example.sambo.utils.ext.setCornerRadius
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment() {
    private val args: DetailsFragmentArgs by navArgs()
    override fun resID() = R.layout.fragment_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

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
        ivNewsDetails.transitionName =
            resources.getString(R.string.image_transition, args.details?.id)
        tvTitle.text = args.details?.title
        tvDescription.text = args.details?.content
        Picasso.get()
            .load(args.details?.preview)
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