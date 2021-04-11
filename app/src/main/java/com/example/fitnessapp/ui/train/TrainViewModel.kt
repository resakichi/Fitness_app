package com.example.fitnessapp.ui.train

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val TAG = "Firebase Data"

class TrainViewModel : ViewModel() {

    val trainList: LiveData<State<Train>>
        get() = _trainList
    private var _trainList: MutableLiveData<State<Train>> = MutableLiveData()


    fun getTrains(){
        _trainList.postValue(State.Loading())

        viewModelScope.launch {
            loadData()
        }

    }

    private suspend fun loadData() {
        withContext(Dispatchers.IO) {
            val result_list: MutableList<Exercises> = mutableListOf()
            val list: MutableList<Train> = mutableListOf()
            val docRef = Firebase.firestore.collection("Program").get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        list.add(Train(document!!.getString("name")!!,
                        document!!.getString("image")!!))
                    }
                    _trainList.postValue(State.Success(data = list))
                }
        }
    }
}