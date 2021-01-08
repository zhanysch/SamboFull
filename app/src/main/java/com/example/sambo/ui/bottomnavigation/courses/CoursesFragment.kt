package com.example.sambo.ui.bottomnavigation.courses

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sambo.R
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import com.example.sambo.ui.bottomnavigation.BaseFragment
import com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment.CoursesAdapter
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.BottomSheet
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.ItemListener
import kotlinx.android.synthetic.main.fragment_courses.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CoursesFragment: BaseFragment(), ItemListener {
    override fun resID() = R.layout.fragment_courses
    private val vm by sharedViewModel<CoursesViewModel>()
    private val adapter = CoursesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.loadList()
        vm.text.observe(viewLifecycleOwner, Observer {
                 // изминен текста при клике на recyclerview
                textChange.text = it.title
              vm.choosedCategory(it)
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

    override fun itemsClick(item: BottomSheetRows) {
      vm.choosedCategory(item)
    }
}