package com.example.fitnessapp.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel.get_user_data(userID = userId)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle = arguments
        var userID: String = "none"
        if (bundle != null){
            userID = bundle.getString("userID").toString()
        }
        val bindings: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        bindings.bottomNav.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.get_user_data(userID = userID)
        viewModel.user_data.observe(viewLifecycleOwner, Observer {
            Log.d("User_data", it.toString())
            requireView().findViewById<TextView>(R.id.email_tv).text = it.email
            requireView().findViewById<TextView>(R.id.userID_tv).text = it.password
            requireView().findViewById<TextView>(R.id.weight_tv).text = it.weight
            requireView().findViewById<TextView>(R.id.height_tv).text = it.height
            requireView().findViewById<TextView>(R.id.age_tv).text = it.age
        })
    }

}