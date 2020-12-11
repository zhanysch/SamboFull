package com.example.sambo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sambo.R
import com.example.sambo.ui.bottomnavigation.*
import com.example.sambo.ui.bottomnavigation.courses.CoursesFragment
import com.example.sambo.ui.bottomnavigation.profilehelper.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNav()
        setupListeners()
    }

    private fun setupBottomNav() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        adapter.addFragment(HomeFragment())
        adapter.addFragment(CompetitionFragment())
        adapter.addFragment(CoursesFragment())
        adapter.addFragment(ProfileFragment())
    }

    private fun setupListeners() {
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> viewPager.currentItem = 0
                R.id.competition -> viewPager.currentItem = 1
                R.id.courses -> viewPager.currentItem = 2
                R.id.profile -> viewPager.currentItem = 3
            }

            return@setOnNavigationItemSelectedListener true
        }
    }
}