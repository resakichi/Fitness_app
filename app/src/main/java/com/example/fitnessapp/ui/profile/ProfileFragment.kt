package com.example.fitnessapp.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.fitnessapp.R
import com.example.fitnessapp.data.User
import com.example.fitnessapp.model.FirebaseModel
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)


        requireView().findViewById<Button>(R.id.register_btn).setOnClickListener {
            val login_edit: String = requireView().findViewById<EditText>(R.id.login_edit).text.toString()
            val password_edit: String = requireView().findViewById<EditText>(R.id.password_edit).text.toString()
            val weight_edit: String = requireView().findViewById<EditText>(R.id.weight_edit).text.toString()
            val height_edit: String = requireView().findViewById<EditText>(R.id.height_edit).text.toString()
            val age_edit: String = requireView().findViewById<EditText>(R.id.age_edit).text.toString()

            val user_data = User(login_edit, password_edit, weight_edit, height_edit, age_edit)
            FirebaseModel.user_register(user_data)
        }
    }

}