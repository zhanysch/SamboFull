package com.example.sambo.ui.bottomnavigation.courses

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.sambo.R
import com.example.sambo.data.modelBottomSheet.BottomSheetRows
import com.example.sambo.ui.bottomnavigation.BaseFragment
import com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment.CoursesAdapter
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.BottomSheet
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.BottomSheetAdapter
import com.example.sambo.ui.bottomnavigation.courses.bottomsheet.ItemListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheethelper.*
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.fragment_courses.view.*
import kotlinx.android.synthetic.main.view_bottom_sheet.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFragment: BaseFragment(), ItemListener {
    override fun resID() = R.layout.fragment_courses
    private val vm by sharedViewModel<CoursesViewModel>()
    private val adapter = CoursesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.loadList()
        vm.text.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty())         // изминен текста при клике
                textChange.text = it
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

    }


}