package com.example.fitnessapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.data.PreferenceHelper.customPreference
import com.example.fitnessapp.data.PreferenceHelper.userID
import com.example.fitnessapp.databinding.ActivityMainBinding

val PERF_NAME = "USER_ID"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //find navcontroller and setup bottomnav
        val navController = findNavController(R.id.nav_host_container)
        binding.bottomNav.setupWithNavController(navController)
        val userId = this.intent.getStringExtra("userID")
        val email = this.intent.getStringExtra("email")
        val password = this.intent.getStringExtra("password")
        Log.d("MAIN", "${userId}")
        val sharedPref = customPreference(this, PERF_NAME)
        sharedPref.userID = userId/*
        sharedPref.userEmail = email
        sharedPref.password = password
        */
    }

}