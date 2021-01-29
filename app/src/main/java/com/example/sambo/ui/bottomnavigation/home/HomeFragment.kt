package com.example.sambo.ui.bottomnavigation.home

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SnapHelper
import com.example.sambo.R
import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.ui.bottomnavigation.BaseFragment
import com.example.sambo.utils.decorators.GravitySnapHelper
import com.example.sambo.utils.decorators.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.navigation.fragment.findNavController

class HomeFragment : BaseFragment() {

    override fun resID() = R.layout.fragment_home
    private val vm by sharedViewModel<HomeViewModel>()
    private val cardsAdapter by lazy { CardsAdapter() { navigateToNewsDetails(it) } }
    private val collectionsAdapter by lazy { CollectionsAdapter() { navigateToCollectionsDetails(it) } }
    private val newsAdapter by lazy { NewsAdapter() { navigateToNewsDetails(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
        setupListeners()

        vm.loadCards()
        vm.loadCollections()
        vm.loadNews()
    }

    private fun setupListeners() {
        btnAdvice.setOnClickListener {
            vm.adviceOfDay()
        }
    }

    private fun setupRecyclerView() {
        recyclerViewCards.adapter = cardsAdapter
        recyclerViewCollections.adapter = collectionsAdapter
        recyclerviewNews.adapter = newsAdapter

        val helper: SnapHelper = GravitySnapHelper(Gravity.START)
        helper.attachToRecyclerView(recyclerViewCards)

        recyclerViewCards.addItemDecoration(
            ItemOffsetDecoration(
                itemLeftMargin = 10f,
                itemRightMargin = 10f,
                firstItemLeftMargin = 20f,
                lastItemRightMargin = 20f
            )
        )

        recyclerViewCollections.addItemDecoration(
            ItemOffsetDecoration(
                itemLeftMargin = 8f,
                itemRightMargin = 8f,
                firstItemLeftMargin = 20f,
                lastItemRightMargin = 20f
            )
        )

        recyclerviewNews.addItemDecoration(
            ItemOffsetDecoration(
                itemBottomMargin = 28f
            )
        )
    }

    private fun setupViewModel() {
        vm.cardsData.observe(viewLifecycleOwner, Observer {
            cardsAdapter.submitList(it.rows)
        })

        vm.collectionsData.observe(viewLifecycleOwner, Observer {
            collectionsAdapter.submitList(it.rows)
        })

        vm.newsData.observe(viewLifecycleOwner, Observer {
            newsAdapter.submitList(it.rows)
        })
        vm.adviceData.observe(viewLifecycleOwner, Observer {
            navigateToAdviceDetails(it)
        })
    }

    private fun navigateToNewsDetails(data: RowsItem) {
        val destination = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(data)
        findNavController().navigate(destination)
    }

    private fun navigateToCollectionsDetails(data: RowsItem) {
        val destination =
            HomeFragmentDirections.actionHomeFragmentToCollectionsDetailsFragment(data)
        findNavController().navigate(destination)
    }

    private fun navigateToAdviceDetails(data: AdviceOfDayModel) {
        val destination = HomeFragmentDirections.actionHomeFragmentToAdviceDetailsFragment(data)
        findNavController().navigate(destination)
    }
}