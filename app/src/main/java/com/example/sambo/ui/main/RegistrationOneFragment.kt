package com.example.sambo.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.local.PreferenceHelper
import kotlinx.android.synthetic.main.activity_registone.*

class RegistrationOneFragment : BaseFragment() {
    override fun getviewId() = R.layout.activity_registone


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        btnRegistr.setOnClickListener {
            PreferenceHelper.setIsLogetIn(true)
            findNavController().navigate(R.id.action_registrationOneFragment_to_registrationTwoFragment2)
        }

        btnSign.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}