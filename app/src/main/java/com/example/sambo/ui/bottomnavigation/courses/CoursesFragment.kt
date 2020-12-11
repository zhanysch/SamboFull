package com.example.sambo.ui.bottomnavigation.courses

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.sambo.R
import com.example.sambo.ui.bottomnavigation.BaseFragment
import com.example.sambo.ui.bottomnavigation.courses.adapterForCourseFragment.CoursesAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_courses.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFragment: BaseFragment() {
    override fun resID() = R.layout.fragment_courses
    private val vm by viewModel<CoursesViewModel>()
    private val adapter = CoursesAdapter()
    private var bottomSheetBehavior: BottomSheetBehavior<TextView>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_courses.adapter = adapter
        vm.data.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        textChange.setOnClickListener {
            val bottomSheet = BottomSheet()
            bottomSheet.show(childFragmentManager,"tdsg")
        }


    }



   /* private fun setupBottomSheet() {
      *//*  bottomSheetBehavior = BottomSheetBehavior.from(textChange) // (bottomSheet) id xml file viewbottomsheet    // troubles with
        textChange.setOnClickListener {
            if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED){         !!!!!// bottomsheet dialog
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            } else if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED){
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            }

        }*//*

        bottomSheetBehavior?.addBottomSheetCallback(object :
        BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.d("fdgdf","fdds")
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("fdgdf","fdds")
            }

        })
    }*/

}