package com.example.sambo.ui.bottomnavigation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.commonpagination.BaseFragment
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.utils.decorators.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_collections_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CollectionsDetailsFragment : BaseFragment() {
    override fun resID() = R.layout.fragment_collections_details
    private val vm by sharedViewModel<CollectionsDetailsViewModel>()
    private val collectionsDetailsAdapter by lazy {CollectionsDetailsAdapter() { navigateToNewsDetails(it) }}

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
        recyclerViewCollectionsDetails.adapter = collectionsDetailsAdapter

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
        val bundle = CollectionsDetailsFragmentArgs.fromBundle(requireArguments())
        tvCollectionsTitle.text = bundle.collections?.title

        val categoryId = bundle.collections?.id
        categoryId?.let { vm.loadSelectionsData(it) }
    }

    private fun navigateToNewsDetails(data: RowsItem) {
        val destination = CollectionsDetailsFragmentDirections.actionCollectionsDetailsFragmentToNewsDetailsFragment(data)
        findNavController().navigate(destination)
    }
}