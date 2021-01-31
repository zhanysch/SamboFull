package com.example.sambo.utils.ext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <reified T : AppCompatActivity> AppCompatActivity.launchActivity() {
    this.startActivity(Intent(this, T::class.java))
}

inline fun <reified T : AppCompatActivity> FragmentActivity.launchActivity() {
    this.startActivity(Intent(this, T::class.java))
}

inline fun <reified T> Fragment.cleanLaunchActivity() {
    this.activity?.finish()
    startActivity(
        Intent(context, T::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}