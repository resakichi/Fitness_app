package com.example.fitnessapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import com.example.fitnessapp.data.PreferenceHelper.customPreference
import com.example.fitnessapp.data.PreferenceHelper.password
import com.example.fitnessapp.data.PreferenceHelper.userEmail
import com.example.fitnessapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth


val CUSTOM_PERF_NAME = "user_data"

class login : Fragment() {

    companion object {
        fun newInstance() = login()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val perf = customPreference(requireContext(), CUSTOM_PERF_NAME)
        if (perf.userEmail != null && perf.password != null){
            val intent = Intent(requireContext(), MainActivity::class.java)
            //intent.putExtra("userID", perf.userID)
            startActivity(intent)
            requireActivity().finish()
        }
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        requireView().findViewById<Button>(R.id.login_btn).setOnClickListener {

            val email: String = requireView().findViewById<EditText>(R.id.email_login).text.toString().trim{it <= ' '}
            val password: String = requireView().findViewById<EditText>(R.id.password_login).text.toString().trim{it <= ' '}

            when {
                TextUtils.isEmpty(email) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(password) -> {
                    Toast.makeText(
                        requireContext(),
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            val data = task.result!!.user!!.uid

                            val pref = customPreference(requireContext(), CUSTOM_PERF_NAME)
                            pref.userEmail = email
                            pref.password = password

                            val intent = Intent(requireContext(), MainActivity::class.java)
                            intent.putExtra("userID", data)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                        else{
                            Log.e("LOGIN_Failrue", task.exception!!.message.toString())
                        }
                    }
                }
            }
        }
        requireView().findViewById<TextView>(R.id.tv_register_login).setOnClickListener {
            findNavController().navigate(R.id.register)
        }
    }

}