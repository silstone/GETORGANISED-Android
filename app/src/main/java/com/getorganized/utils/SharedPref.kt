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

    fun get_value(context: Context,key: String?): String? {
        return getSharedPreferences(context).getString(key, null)
    }

    fun save_value(context: Context, key: String? ,newValue: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(key, newValue)
        editor.commit()
    }

    fun get_bool_value(context: Context,key: String?): Boolean {
        return getSharedPreferences(context).getBoolean(key, false)
    }
    fun save_bool_value(context: Context, key: String? ,newValue: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean(key, newValue)
        editor.commit()
    }

    fun clear_data(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.clear()
        editor.commit()
    }


}