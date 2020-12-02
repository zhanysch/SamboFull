package com.example.sambo.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

inline fun <reified T: AppCompatActivity> AppCompatActivity.launchActivity() {
    this.startActivity(Intent(this,T::class.java))
}
inline fun <reified T: AppCompatActivity> FragmentActivity.launchActivity() {
    this.startActivity(Intent(this,T::class.java)) }