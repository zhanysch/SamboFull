package com.example.sambo.ui.bottomnavigation.courses

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.common.BaseFragment
import com.example.sambo.data.model.cards.RowsItem
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment.CoursesAdapter
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.BottomSheet
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.ItemListener
import kotlinx.android.synthetic.main.fragment_courses.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoursesFragment: BaseFragment(), ItemListener {
    override fun resID() = R.layout.fragment_courses
    private val vm by sharedViewModel<CoursesViewModel>()
    private val adapter = CoursesAdapter(){
        navigateToNewsDetails(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.loadList()
        vm.text.observe(viewLifecycleOwner, Observer {
                 // изминен текста при клике на recyclerview
                textChange.text = it.title
              vm.choosedCategory(it)  // подгрузка картины при клике на категорию
        })

        recycler_courses.adapter = adapter
        vm.data.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        textChange.setOnClickListener {
            val bottomSheet = BottomSheet()
            bottomSheet.show(childFragmentManager,"tdsg")
        }
    }

    private fun navigateToNewsDetails(data: RowsItem) {
        val destination = CoursesFragmentDirections.actionCoursesFragmentToNewsDetailsFragment(data)
        findNavController().navigate(destination)
    }

    override fun itemsClick(item: BottomSheetRows) {   // для подгрузки  оперделенных данных при выборе категории
      vm.choosedCategory(item)
    }
}