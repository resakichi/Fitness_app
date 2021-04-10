package com.example.fitnessapp.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentArticleInfoBinding


class ArticleInfoFragment : Fragment() {

    private lateinit var binding: FragmentArticleInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       binding = FragmentArticleInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

}