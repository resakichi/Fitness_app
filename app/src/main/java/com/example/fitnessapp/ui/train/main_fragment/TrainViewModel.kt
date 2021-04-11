package com.example.fitnessapp.ui.train.main_fragment

import android.util.Log
import androidx.lifecycle.*
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.model.FirebaseModel.Companion.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainViewModel : ViewModel() {

    val trainList: LiveData<State<Train>>
        get() = _trainList
    private var _trainList: MutableLiveData<State<Train>> = MutableLiveData()


    fun getTrains(){
        if (_trainList.value?.data.isNullOrEmpty()){
            viewModelScope.launch {
                loadData()
            }
        }


    }

    private suspend fun loadData(){
        withContext(Dispatchers.IO){

            _trainList.postValue(State.Loading())

            val res: MutableList<Train> = mutableListOf()

            database.collection("Programs")
                .get().addOnSuccessListener { result ->
                    for (document in result) {
                        res.add(
                            Train(
                                image = document.data.getValue("image") as String,
                                name =  document.data.getValue("name") as String,
                                id = document.id
                            )
                        )

                    }
                    _trainList.postValue(State.Success(res))
                }
                .addOnFailureListener { exception ->
                    _trainList.postValue(State.Error(message = "Some error occurred: ${exception.message}"))
                }
        }
    }
}