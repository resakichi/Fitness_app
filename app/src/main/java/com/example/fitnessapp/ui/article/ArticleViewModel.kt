package com.example.fitnessapp.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Article
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.model.FirebaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleViewModel : ViewModel() {

    val articleList: LiveData<State<Article>>
        get() = _articleList
    private var _articleList: MutableLiveData<State<Article>> = MutableLiveData()

    fun getArticles() {
        if (_articleList.value?.data.isNullOrEmpty()) {
            viewModelScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        withContext(Dispatchers.IO) {
            _articleList.postValue(State.Loading())

            val res: MutableList<Article> = mutableListOf()

            FirebaseModel.database.collection("Articles")
                .get().addOnSuccessListener { result ->
                    for (document in result) {
                        res.add(
                            Article(
                                title = document.data.getValue("title") as String,
                                image = document.data.getValue("image") as String,
                                category = document.data.getValue("category") as String,
                                subtitle = document.data.getValue("subtitle") as String,
                                description = document.data.getValue("description") as String,
                            )
                        )
                        Log.d("lalala", "${document.id} => ${document.data}")

                    }
                    _articleList.postValue(State.Success(res))
                }
                .addOnFailureListener { exception ->
                    Log.d("lalala", "Error getting documents: ", exception)
                    _articleList.postValue(State.Error(message = "Some error occurred"))
                }
        }
    }
}