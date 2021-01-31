package com.example.sambo.data.local

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFERENCE_NAME = "FitnessPreference"
    private const val IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"
    private const val FOR_REGISTR= "FOR_REGISTR"

    private var preference: SharedPreferences? = null

    fun init(context: Context){
        preference = context.getSharedPreferences(
            PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun setIsFirstLaunch(){
        preference?.edit()?.putBoolean(
            IS_FIRST_LAUNCH,false)?.apply()
    }

    fun setIsLogetIn(bolean : Boolean){
        preference?.edit()?.putBoolean(
            FOR_REGISTR,bolean)?.apply()
    }

    fun getLogetIn() = preference?.getBoolean(
        FOR_REGISTR,false) ?: false

    fun getIsFirstLaunch() = preference?.getBoolean(
        IS_FIRST_LAUNCH,true) ?: true
}