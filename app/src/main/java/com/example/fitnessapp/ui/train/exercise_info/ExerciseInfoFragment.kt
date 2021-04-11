package com.example.fitnessapp.ui.train.exercise_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.databinding.FragmentExerciseInfoBinding
import com.squareup.picasso.Picasso


class ExerciseInfoFragment : Fragment() {

    private lateinit var binding: FragmentExerciseInfoBinding
    private lateinit var name: String
    private var repeats: Long? = null
    private var approaches: Long? = null
    private lateinit var image: String
    private lateinit var description: String
    private lateinit var rest: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            name = it.getString("name")!!
            repeats = it.getLong("repeats")
            approaches = it.getLong("approaches")
            image = it.getString("image").toString()
            description = it.getString("description")!!
            rest = it.getString("rest")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExerciseInfoBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.description.text = description
        binding.approaches.text = approaches.toString()
        binding.toolbar.title = name
        binding.repeats.text = repeats.toString()
        binding.rest.text = rest
        Picasso.get()
            .load(image)
            .into(binding.image)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }

}