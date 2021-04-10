package com.example.fitnessapp.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Article
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleViewModel : ViewModel() {

    val articleList: LiveData<State<Article>>
        get() = _articleList
    private var _articleList: MutableLiveData<State<Article>> = MutableLiveData()

    fun getArticles() {
        _articleList.postValue(State.Loading())

        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        withContext(Dispatchers.IO) {
            val list: MutableList<Article> = mutableListOf()
            for (i in 0..10) {
                list.add(
                    Article(
                        R.drawable.food,
                        "Еда",
                        "Очень нормас",
                         "Нормас еда, отвечаю"
                    ))
            }

            Thread.sleep(3000)
            _articleList.postValue(State.Success(data = list))
        }
    }
}