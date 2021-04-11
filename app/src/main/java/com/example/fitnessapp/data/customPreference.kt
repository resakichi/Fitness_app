package com.example.fitnessapp.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferenceHelper{
    val APP_USER_EMAIL = "USER_EMAIL"
    val APP_USER_ID = "USER_ID"
    val APP_USER_PASSWORD = "USER_PASSWORD"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation:
                                            (SharedPreferences.Editor) -> Unit){
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }
    var SharedPreferences.userEmail
        get() = getString(APP_USER_EMAIL, null)
        set(value){
            editMe { it.putString(APP_USER_EMAIL, value) }
        }
    var SharedPreferences.password
        get() = getString(APP_USER_PASSWORD, null)
        set(value){
            editMe { it.putString(APP_USER_PASSWORD, value) }
        }
    var SharedPreferences.userID
        get() = getString(APP_USER_ID, null)
        set(value){
            editMe { it.putString(APP_USER_ID, value) }
        }
    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}