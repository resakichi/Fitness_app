package com.example.fitnessapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.data.User
import com.example.fitnessapp.model.FirebaseModel

class ProfileViewModel: ViewModel() {

    val user_data: LiveData<User>
        get() = _user_data
    private var _user_data: MutableLiveData<User> = MutableLiveData()

    fun get_user_data(userID: String){
        Log.d("User_data", "Working")
        val query = FirebaseModel.database.collection("Users")
            .whereEqualTo("password", userID)
            .get()
            .addOnSuccessListener { documents ->
                var element = User("none", "none", "none", "none", "none",)
                Log.d("User_data", documents.isEmpty.toString())
                for (document in documents) {
                    element!!.email = document!!.getString("email")!!
                    element!!.password = document!!.getString("password")!!
                    element!!.weight = document!!.getString("weight")!!
                    element!!.height = document!!.getString("height")!!
                    element!!.age = document!!.getString("age")!!
                    _user_data.postValue(element)
                }
            }
            .addOnCanceledListener {
                Log.d("User_data", "Fail getting data")
            }
    }
}