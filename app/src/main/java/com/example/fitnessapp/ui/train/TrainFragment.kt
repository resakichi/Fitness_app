package com.example.fitnessapp.ui.train

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.TrainListAdapter
import com.example.fitnessapp.adapter.TrainListVH
import com.example.fitnessapp.adapter.VerticalSpaceItemDecoration
import com.example.fitnessapp.data.Train
import com.example.fitnessapp.data.network.State
import com.example.fitnessapp.databinding.FragmentTrainBinding

class TrainFragment : Fragment() {



    private lateinit var viewModel: TrainViewModel
    private lateinit var binding: FragmentTrainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.trainRecycler.layoutManager = layoutManager
        val dividerItemDecoration = VerticalSpaceItemDecoration(20)
        binding.trainRecycler.addItemDecoration(dividerItemDecoration)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrainViewModel::class.java)
        viewModel.getTrains()

        dataObserver()
    }

    fun dataObserver(){
        viewModel.trainList.observe(viewLifecycleOwner, Observer{
            when(it){
                is State.Loading ->
                    Toast.makeText(requireContext(), "Список загружается", Toast.LENGTH_SHORT)
                        .show()
                is State.Success -> {
                    binding.trainRecycler.adapter = TrainListAdapter(it.data as List<Train>)
                }
                is State.Error -> Log.e("lallala", "Error")
            }
        })
    }

}