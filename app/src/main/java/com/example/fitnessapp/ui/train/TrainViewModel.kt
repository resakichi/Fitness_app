package com.example.fitnessapp.ui.train

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
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
            val list: MutableList<Train> = mutableListOf()
            for (i in 0..10){
                list.add(Train("Объемные руки", R.drawable.train_placeholder))
            }

            Thread.sleep(2000)
            _trainList.postValue(State.Success(data = list))
        }
    }
}