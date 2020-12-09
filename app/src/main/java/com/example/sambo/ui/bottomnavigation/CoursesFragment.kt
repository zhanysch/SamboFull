package com.example.sambo.ui.bottomnavigation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sambo.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.view_bottom_sheet.*

class CoursesFragment: BaseFragment() {
    override fun resID() = R.layout.fragment_courses
    private var bottomSheetBehavior: BottomSheetBehavior<TextView>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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