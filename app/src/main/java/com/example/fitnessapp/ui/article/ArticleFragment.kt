package com.example.fitnessapp.ui.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.ArticleListAdapter
import com.example.fitnessapp.data.Article
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)

        binding.articleRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        viewModel.getArticles()

        dataObserver()
    }

    fun dataObserver() {
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> Toast.makeText(
                    requireContext(),
                    "Список загружается",
                    Toast.LENGTH_SHORT
                ).show()
                is State.Success -> {
                    binding.articleRecyclerView.adapter = ArticleListAdapter(it.data as List<Article>)
                }
                is State.Error -> Log.e("lalala", "Article loading error occurred")
            }
        })
    }

}