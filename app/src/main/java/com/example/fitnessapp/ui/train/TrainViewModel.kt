package com.example.fitnessapp.ui.train

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.model.FirebaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private suspend fun loadData(){
        withContext(Dispatchers.IO){
            val data = FirebaseModel.get_training()
            val list: MutableList<Train> = mutableListOf()

            _trainList.postValue(State.Success(data = list))
        }
    }
}