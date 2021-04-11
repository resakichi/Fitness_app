package com.example.fitnessapp.data

data class Exercises(
    val id: String,
    val name: String,
    val repeats: Long,
    val approaches: Long,
    val image: String,
    val exerciseImage: String,
    val description: String,
    val rest: String
)
