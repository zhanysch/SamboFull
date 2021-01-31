package com.example.sambo.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.sambo.R
import com.example.sambo.data.common.BaseFragment
import com.example.sambo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_regist.*

class RegistrationTwoFragment : BaseFragment(){
    override fun resID() = R.layout.activity_regist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        btnsamb.setOnClickListener {

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)


        }
        btnTren.setOnClickListener {

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }
        btnParent.setOnClickListener {

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }
    }
}