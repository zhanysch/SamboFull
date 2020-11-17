package com.example.sambo

import android.app.Application
import com.example.sambo.data.local.PreferenceHelper

class SamboApp:Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(applicationContext)
    }
}