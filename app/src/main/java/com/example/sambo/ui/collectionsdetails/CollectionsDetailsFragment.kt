package com.example.sambo.ui.collectionsdetails

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sambo.R
import com.example.sambo.data.common.BaseFragment
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.utils.decorators.ItemOffsetDecoration
import com.example.sambo.utils.ext.toTransitionGroup
import kotlinx.android.synthetic.main.fragment_collections_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CollectionsDetailsFragment : BaseFragment() {
    override fun resID() = R.layout.fragment_collections_details
    private val vm by sharedViewModel<CollectionsDetailsViewModel>()
    private val args: CollectionsDetailsFragmentArgs by navArgs()
    private val collectionsDetailsAdapter by lazy {
        CollectionsDetailsAdapter() { item, image ->
            navigateToNewsDetails(item, image)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        vm.collectionsData.observe(viewLifecycleOwner, Observer {
            collectionsDetailsAdapter.submitList(it.rows)
        })
    }

    private fun setupRecyclerView() {
        recyclerViewCollectionsDetails.apply {
            adapter = collectionsDetailsAdapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        recyclerViewCollectionsDetails.addItemDecoration(
            ItemOffsetDecoration(
                itemBottomMargin = 20f,
                isNeedBottomMargin = false
            )
        )
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupViews() {
        tvCollectionsTitle.text = args.collections?.title

        val categoryId = args.collections?.id
        categoryId?.let { vm.loadSelectionsData(it) }
    }

    private fun navigateToNewsDetails(data: RowsModel, image: ImageView) {
        val extras = FragmentNavigatorExtras(image.toTransitionGroup())
        val destination =
            CollectionsDetailsFragmentDirections.actionCollectionsDetailsFragmentToNewsDetailsFragment(
                data
            )
        findNavController().navigate(destination, extras)
    }
}