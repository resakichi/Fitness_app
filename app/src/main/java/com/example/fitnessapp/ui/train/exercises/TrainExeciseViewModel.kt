package com.example.fitnessapp.ui.train.exercises

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.model.FirebaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainExerciseViewModel() : ViewModel(){

    val exerciseList: LiveData<State<Exercises>>
        get() = _exerciseList
    private var _exerciseList: MutableLiveData<State<Exercises>> = MutableLiveData()

    fun getExercises(collectionName: String){
        if (_exerciseList.value?.data.isNullOrEmpty()){
            viewModelScope.launch {
                loadData(collectionName)
            }
        }

    }

    private suspend fun loadData(documentId: String) {
        withContext(Dispatchers.IO) {

            _exerciseList.postValue(State.Loading())

            val res: MutableList<Exercises> = mutableListOf()

            FirebaseModel.database.collection("Programs").document(documentId).collection("Exercises")
                .get().addOnSuccessListener { result ->
                    for (document in result) {
                        res.add(
                            Exercises(
                                image = document.data.getValue("image") as String,
                                repetitions = 0, //document.data.getValue("repetitions") as Long,
                                apporoach = 0, // document.data.getValue("apporoach") as Long,
                                rec_weight = 0, // document.data.getValue("rec_weight") as Long,
                                name = document.data.getValue("name") as String,
                                id = document.id
                            )
                        )
                        Log.d("lalala", "${document.id} => ${document.data}")

                    }
                    _exerciseList.postValue(State.Success(res))
                }
                .addOnFailureListener { exception ->
                    Log.d("lalala", "Error getting documents: ", exception)
                    _exerciseList.postValue(State.Error(message = "Some error occurred"))
                }
        }
    }
}