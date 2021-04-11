package com.example.fitnessapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //find navcontroller and setup bottomnav
        val navController = findNavController(R.id.nav_host_container)
        binding.bottomNav.setupWithNavController(navController)
        findNavController(R.id.nav_host_container).navigate(R.id.loginFragment)
        binding.bottomNav.visibility = View.INVISIBLE
    }


}