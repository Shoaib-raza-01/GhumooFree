package com.example.ghumoo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var email : TextView
    private lateinit var password :TextView
    lateinit var loginBtn :Button
    private lateinit var auth :FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_login, container, false)
        email = v.findViewById(R.id.editTextTextEmailAddress)
        password = v.findViewById(R.id.editTextTextPassword)
        loginBtn = v.findViewById(R.id.Login)
        auth = Firebase.auth
        loginBtn.setOnClickListener { 
            loginUser()
        }
        return v
    }
    private fun loginUser(){
        val Email = email.text.toString()
        val Password = password.text.toString()
        if (Email.isEmpty() || Password.isEmpty()){
            Toast.makeText(requireActivity(), "Email and Password can't be empty", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener{
                if(it.isSuccessful){
                    if(auth.currentUser!!.isEmailVerified){
                        Toast.makeText(requireActivity(), "Login Was Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(requireActivity(),HomePage::class.java))
                    }
                }else{
                    Toast.makeText(requireActivity(), "Invalid Credentials!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}