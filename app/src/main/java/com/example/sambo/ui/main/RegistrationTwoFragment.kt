package com.example.sambo.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sambo.R
import com.example.sambo.data.local.PreferenceHelper
import kotlinx.android.synthetic.main.activity_regist.*

class RegistrationTwoFragment :BaseFragment(){
    override fun getviewId() = R.layout.activity_regist
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        btnsamb.setOnClickListener {
            PreferenceHelper.setIsFirtstLaunchSecond()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)


        }
        btnTren.setOnClickListener {
            PreferenceHelper.setIsFirtstLaunchSecond()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }
        btnParent.setOnClickListener {
            PreferenceHelper.setIsFirtstLaunchSecond()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }
    }

}