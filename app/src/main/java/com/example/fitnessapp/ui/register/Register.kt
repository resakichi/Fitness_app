package com.example.fitnessapp.ui.register

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.data.User

class Register : Fragment() {

    companion object {
        fun newInstance() = Register()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val user_data = User("example@mail.com",
            "none",
            "none",
            "none",
            "none")

        requireView().findViewById<Button>(R.id.register_btn).setOnClickListener {

            user_data.email = requireView().findViewById<EditText>(R.id.email_register).text.toString().trim{it <= ' '}
            user_data.password = requireView().findViewById<EditText>(R.id.password_register).text.toString().trim{it <= ' '}
            user_data.weight = requireView().findViewById<EditText>(R.id.weight_register).text.toString().trim{it <= ' '}
            user_data.height = requireView().findViewById<EditText>(R.id.height_register).text.toString().trim{it <= ' '}
            user_data.age = requireView().findViewById<EditText>(R.id.age_register).text.toString().trim{it <= ' '}

            when {
                TextUtils.isEmpty(user_data.email) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(user_data.password) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(user_data.weight) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter weight",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(user_data.height) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter height",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(user_data.age) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter age",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    viewModel.createUser(user_data)
                    findNavController().navigate(R.id.login)
                }
            }
        }
        requireView().findViewById<TextView>(R.id.tv_login_reg).setOnClickListener {
            findNavController().navigate(R.id.login)
        }
    }

}