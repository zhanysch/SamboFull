package com.example.sambo

import android.app.Application
import android.content.Context
import com.example.sambo.data.local.PreferenceHelper

class SamboApp:Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(applicationContext)
    }
    companion object {     // context создан для того чтобы смогли к нему обратиться с любого класа
        // т.к object FileUtils требует Context!!!!
        private lateinit var context: Context
        fun applicationContext() = context
    }
}