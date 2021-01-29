package com.example.sambo.ui.bottomnavigation.home

import android.os.Bundle
import android.view.View
import com.example.sambo.R
import com.example.sambo.data.commonpagination.BaseFullScreenDialog
import kotlinx.android.synthetic.main.view_bottom_sheet_advice_of_day.*

class AdviceOfDayFullScreenDialog : BaseFullScreenDialog() {
    override fun getLayoutId() = R.layout.view_bottom_sheet_advice_of_day

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViews()
    }

    private fun setupViews() {
        val bundle = AdviceOfDayFullScreenDialogArgs.fromBundle(requireArguments())

        tvAdviceDescription.text = bundle.advice?.description
    }

    private fun setupListeners() {
        btnOk.setOnClickListener {
            dismiss()
        }
    }
}

