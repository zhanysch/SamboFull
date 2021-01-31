package com.example.sambo.ui.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sambo.R

class ForFragmentActivity : AppCompatActivity() {

    private var host : NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forfragment)
        host = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }
}