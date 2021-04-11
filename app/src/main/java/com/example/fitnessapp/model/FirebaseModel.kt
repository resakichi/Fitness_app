package com.example.fitnessapp.model

import android.util.Log
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.User
import com.google.firebase.firestore.ktx.firestore
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
                        Log.w( TAG, "Error adding document", e)
                    }
        }

//        fun get_training(){
//            val data: MutableList<Exercises> = mutableListOf()
//
//            database.collection("Exercises")
//                    .get().addOnSuccessListener { result ->
//                        for (document in result) {
//                            val item = document.data.values as MutableList<Exercises>
//                            Log.d(TAG, "${document.id} => ${document.data}")
//                            val al: List<Exercises> = ArrayList<Exercises>(document.data.values)
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.d(TAG, "Error getting documents: ", exception)
//                    }
////            Log.e(TAG + "result", "${result_list.toString()}")
////            return result_list
//        }

        fun get_image(image: String){
            var islandRef = storageRef.child("sao.jpg")

            val ONE_MEGABYTE: Long = 1024 * 1024
            islandRef.getBytes(ONE_MEGABYTE)
                    .addOnSuccessListener {  }
                    .addOnFailureListener {  }

        }
    }
}