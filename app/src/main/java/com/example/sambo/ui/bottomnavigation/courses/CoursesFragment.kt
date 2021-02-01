package com.example.sambo.ui.bottomnavigation.courses

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.common.BaseFragment
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment.CoursesAdapter
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.BottomSheet
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.ItemListener
import com.example.sambo.utils.ext.toTransitionGroup
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.fragment_courses.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoursesFragment: BaseFragment(), ItemListener {
    override fun resID() = R.layout.fragment_courses
    private val vm by sharedViewModel<CoursesViewModel>()
    private val adapterCourses by lazy {
        CoursesAdapter() { item: RowsModel, image: ShapeableImageView ->
            navigateToDetails(item, image)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.loadList()
        vm.text.observe(viewLifecycleOwner, Observer {

                textChange.text = it.title
              vm.choosedCategory(it)
        })

        recycler_courses.apply {
            adapter = adapterCourses
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        vm.data.observe(viewLifecycleOwner, Observer {
            adapterCourses.submitList(it)
        })

        textChange.setOnClickListener {
            val bottomSheet = BottomSheet()
            bottomSheet.show(childFragmentManager,"tdsg")
        }
    }

    private fun navigateToDetails(data: RowsModel, image: ShapeableImageView) {
        val extras = FragmentNavigatorExtras(
            image.toTransitionGroup()
        )
        val destination = CoursesFragmentDirections.actionCoursesFragmentToNewsDetailsFragment(data)
        findNavController().navigate(destination,extras)
    }

    override fun itemsClick(item: RowsModel) {
        vm.choosedCategory(item)
    }
}