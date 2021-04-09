package com.example.fitnessapp.data.network

sealed class State<T>(
    val data: List<T?>? = null,
    val message: String? = null
) {
    class Success<T>(data: List<T>) : State<T>(data)
    class Loading<T>() : State<T>()
    class Error<T>(data: List<T?>? = null, message: String) : State<T>(data, message)
}