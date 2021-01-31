package com.example.sambo.ui.bottomnavigation.home

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SnapHelper
import com.example.sambo.R
import com.example.sambo.data.common.BaseFragment
import com.example.sambo.data.model.advice.AdviceOfDayModel
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.ui.collectionsdetails.CollectionsAdapter
import com.example.sambo.ui.newsdetails.DetailsAdapter
import com.example.sambo.utils.decorators.GravitySnapHelper
import com.example.sambo.utils.decorators.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import com.example.sambo.utils.ext.toTransitionGroup
import com.google.android.material.imageview.ShapeableImageView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {

    override fun resID() = R.layout.fragment_home
    private val vm by sharedViewModel<HomeViewModel>()
    private val cardsAdapter by lazy {
        CardsAdapter() { item, image ->
            navigateToCardsDetails(item, image)
        }
    }
    private val collectionsAdapter by lazy { CollectionsAdapter() { navigateToCollectionsDetails(it) } }
    private val newsAdapter by lazy {
        DetailsAdapter() { item, image ->
            navigateToNewsDetails(item, image)
        }
    }

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
        recyclerViewCards.apply {
            adapter = cardsAdapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        recyclerViewCollections.adapter = collectionsAdapter
        recyclerViewNews.apply {
            adapter = newsAdapter
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }


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

        recyclerViewNews.addItemDecoration(
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

    private fun navigateToNewsDetails(data: RowsModel, image: ShapeableImageView) {
        val extras = FragmentNavigatorExtras(image.toTransitionGroup())
        val destination = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(data)
        findNavController().navigate(destination, extras)
    }

    private fun navigateToCardsDetails(data: RowsModel, image: ImageView) {
        val extras = FragmentNavigatorExtras(image.toTransitionGroup())
        val destination = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(data)
        findNavController().navigate(destination, extras)
    }

    private fun navigateToCollectionsDetails(data: RowsModel) {
        val destination =
            HomeFragmentDirections.actionHomeFragmentToCollectionsDetailsFragment(data)
        findNavController().navigate(destination)
    }

    private fun navigateToAdviceDetails(data: AdviceOfDayModel) {
        val destination = HomeFragmentDirections.actionHomeFragmentToAdviceDetailsFragment(data)
        findNavController().navigate(destination)
    }
}