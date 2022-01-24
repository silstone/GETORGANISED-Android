package com.getorganized.utils

import android.content.Context
import android.content.SharedPreferences




class SharedPref {

    private val GET_ORGANIZED = "GET_ORGANIZED"


    // properties
    private val USER_EMAIL = "USER_EMAIL"
    // other properties...


    // other properties...
    private fun SharedPreferencesManager() {}

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(GET_ORGANIZED, Context.MODE_PRIVATE)
    }

    fun getemail(context: Context): String? {
        return getSharedPreferences(context).getString(USER_EMAIL, null)
    }

    fun setemail(context: Context, newValue: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(USER_EMAIL, newValue)
        editor.commit()
    }

}