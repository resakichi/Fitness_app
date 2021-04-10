package com.example.fitnessapp.model

import android.util.Log
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
                        Log.w( TAG, "Error adding document", e)
                    }
        }

        fun get_training():MutableList<Exercises> {
            val result_list: MutableList<Exercises> = mutableListOf()
            val source = Source.CACHE
            val docRef = database.collection("Exercises").get(source)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document in task.result!!.documents!!) {
                                Log.d(TAG, "Cached document data: ${document.getField<Int>("apporoach")}")
                                val exercise = Exercises(
                                    title = document.getField<String>("name")!!,
                                    repetition = document.getField<Int>("repetitions")!!,
                                    apporoach = document.getField<Int>("apporoach")!!,
                                    image = document.getField<String>("image")!!,
                                    rec_weight = document.getField<Int>("rec_weight")!!
                                )
                                result_list.add(exercise)
                            }
                        } else {
                            Log.d(TAG, "Cached get failed: ", task.exception)
                        }
                    }
            return result_list
        }

        fun list_training(): MutableList<Exercises> {
            var exercises_list:MutableList<Exercises> = mutableListOf()
            var get_data = object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    exercises_list.clear()
                    if (snapshot!!.exists()){
                        for (data in snapshot.children){
                            val exercises = data.getValue(Exercises::class.java)
                            exercises_list.add(exercises!!)
                        }
                    }
                }
            }
            return exercises_list
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