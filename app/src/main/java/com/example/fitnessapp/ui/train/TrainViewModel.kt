package com.example.fitnessapp.ui.train

import android.util.Log
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
            /*
            val source = Source.CACHE
            val docRef = Firebase.firestore.collection("Exercises").get(source)
                .addOnCompleteListener { task ->
                    if (task.isComplete) {
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
                    }
                    else {
                        Log.d(TAG, "Cached get failed: ", task.exception)
                    }
                }*/
            val list: MutableList<Train> = mutableListOf()
            val docRef = Firebase.firestore.collection("Program").get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        Log.d(TAG, document!!.getString("name")!!)
                        list.add(Train(document!!.getString("name")!!,
                        document!!.getString("image")!!))
                    }
                    _trainList.postValue(State.Success(data = list))
                }

            Log.d(TAG + "res", result_list.toString())
            //list.add(Train("Объемные руки", R.drawable.train_placeholder))
            //list.add(Train("powerlifting", R.drawable.sao))

        }
    }
}