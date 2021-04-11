package com.example.fitnessapp.ui.article

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentArticleInfoBinding
import com.squareup.picasso.Picasso


class ArticleInfoFragment : Fragment() {

    private lateinit var binding: FragmentArticleInfoBinding
    private lateinit var image: String
    private lateinit var description: String
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString("image")!!
            description = it.getString("description")!!
            title = it.getString("title")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleInfoBinding.inflate(inflater, container, false)

        binding.text.text = Html.fromHtml(description)
        Picasso.get()
            .load(image)
            .into(binding.image)
        binding.toolbar.title = title
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

}