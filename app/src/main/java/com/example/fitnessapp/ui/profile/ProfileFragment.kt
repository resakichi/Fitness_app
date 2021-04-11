package com.example.fitnessapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.R

import com.example.fitnessapp.data.PreferenceHelper.clearValues
import com.example.fitnessapp.data.PreferenceHelper.customPreference
import com.example.fitnessapp.data.PreferenceHelper.userID
import com.example.fitnessapp.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth

val CUSTOM_PERF_NAME = "USER_ID"


class ProfileFragment : Fragment() {



    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel.get_user_data(userID = userId)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val sharedPerf = customPreference(requireContext(), CUSTOM_PERF_NAME)
        viewModel.get_user_data(userID = sharedPerf.userID!!)
        viewModel.user_data.observe(viewLifecycleOwner, Observer {
            Log.d("User_data", it.toString())
            requireView().findViewById<TextView>(R.id.email_tv).text = it.email
            requireView().findViewById<TextView>(R.id.userID_tv).text = it.password
            requireView().findViewById<TextView>(R.id.weight_tv).text = it.weight
            requireView().findViewById<TextView>(R.id.height_tv).text = it.height
            requireView().findViewById<TextView>(R.id.age_tv).text = it.age
        })

        requireView().findViewById<Button>(R.id.logout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            sharedPerf.clearValues
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}