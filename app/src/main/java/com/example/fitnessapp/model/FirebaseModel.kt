package com.example.fitnessapp.model

import android.util.Log
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.User
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


private val TAG = "Firebase Data"

class FirebaseModel {
    companion object{

        val database = Firebase.firestore
        var storageRef: StorageReference = FirebaseStorage.getInstance().getReference()

        fun user_register(user: User) {
            database.collection("Users")
                    .add(user)
                    .addOnSuccessListener { documentRefererence ->
                        Log.d(TAG, "DocumentSnapshot added with ID ${documentRefererence.id}")
                    }
                    .addOnFailureListener {e ->
                        Log.w( TAG, "Error adding document:", e)
                    }
        }

        fun get_image(image: String){
            var islandRef = storageRef.child("sao.jpg")

            val ONE_MEGABYTE: Long = 1024 * 1024
            islandRef.getBytes(ONE_MEGABYTE)
                    .addOnSuccessListener {  }
                    .addOnFailureListener {  }

        }
    }
}