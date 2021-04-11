package com.example.fitnessapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.data.PreferenceHelper.clearValues
import com.example.fitnessapp.data.PreferenceHelper.customPreference

val APP_PERF_NAME = "LOGIN_PERF"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findNavController(R.id.nav_host_container_login).navigate(R.id.login)
        val sharedPref = customPreference(this, APP_PERF_NAME)
        sharedPref.clearValues
    }
}