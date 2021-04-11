package com.example.fitnessapp.ui.train.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.ExerciseListAdapter
import com.example.fitnessapp.adapter.TrainListAdapter
import com.example.fitnessapp.adapter.VerticalSpaceItemDecoration
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.databinding.FragmentTrainExerciseListBinding


class TrainPlaceFragment : Fragment() {

    private lateinit var binding: FragmentTrainExerciseListBinding
    private lateinit var viewModel: TrainExerciseViewModel

    private lateinit var id: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id").toString()
            name = it.getString("name").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainExerciseListBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.exercisesRecycler.layoutManager = layoutManager
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.exercisesRecycler.addItemDecoration(dividerItemDecoration)
        binding.exerciseName.text = name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrainExerciseViewModel::class.java)
        viewModel.getExercises(id)

        dataObserver()
    }

    private fun dataObserver() {
        viewModel.exerciseList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    binding.content.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }

                is State.Success -> {
                    binding.content.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.exercisesRecycler.adapter = ExerciseListAdapter(it.data as List<Exercises>)
                }
                is State.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                }
            }
        })
    }


}