package com.example.sambo.ui.bottomnavigation.courses.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.sambo.R
import com.example.sambo.data.model.listing.RowsModel
import com.example.sambo.ui.bottomnavigation.courses.CoursesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.view_bottom_sheet.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BottomSheet: BottomSheetDialogFragment(),ItemListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_bottom_sheet,container,false)
    }

    private val vm by sharedViewModel<CoursesViewModel>()
    private val adapter = BottomSheetAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerBottomsheet.adapter = adapter
        vm.dataCategory.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun itemsClick(item: RowsModel) {
       vm.text.postValue(item)

    }

}