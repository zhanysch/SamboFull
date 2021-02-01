package com.example.sambo.ui.adviceofday

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.sambo.R
import com.example.sambo.data.common.BaseFullScreenDialog
import kotlinx.android.synthetic.main.view_bottom_sheet_advice_of_day.*

class AdviceOfDayFullScreenDialog : BaseFullScreenDialog() {
    override fun getLayoutId() = R.layout.view_bottom_sheet_advice_of_day
    private val args: AdviceOfDayFullScreenDialogArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViews()
    }

    private fun setupViews() {
        tvAdviceDescription.text = args.advice?.description
    }

    private fun setupListeners() {
        btnOk.setOnClickListener {
            dismiss()
        }
    }
}
