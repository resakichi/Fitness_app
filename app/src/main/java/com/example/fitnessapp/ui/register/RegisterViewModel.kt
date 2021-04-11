package com.example.fitnessapp.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.data.User
import com.example.fitnessapp.model.FirebaseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private val TAG = "Create user"

class RegisterViewModel : ViewModel() {
    val user_data: FirebaseUser
        get() = _user_data
    private lateinit var _user_data: FirebaseUser

    fun createUser(data: User) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(data.email, data.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _user_data = task.result!!.user!!
                    data.password = _user_data.uid
                    FirebaseModel.user_register(data)
                    Log.i(TAG, "register succcessfuly")
                }
                else{
                    Log.e(TAG, task.exception!!.message.toString())
                }
            }
    }
}