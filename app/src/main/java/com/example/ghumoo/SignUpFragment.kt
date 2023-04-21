package com.example.ghumoo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    lateinit var username : EditText
    lateinit var email : EditText
    private lateinit var password : EditText
    lateinit var signUpBtn : Button

    //Initializing Firebase Auth
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.fragment_sign_up, container, false)
        username = v.findViewById(R.id.editTextTextPersonName)
        email = v.findViewById(R.id.editTextTextEmailAddress2)
        password = v.findViewById(R.id.editTextTextPassword2)
        signUpBtn = v.findViewById(R.id.signUpBtn)
        //initializing the firebase auth object
        auth = Firebase.auth

        signUpBtn.setOnClickListener {
            signUpUser()
        }
        return v
    }
    private fun signUpUser(){
        val Email = email.text.toString()
        val pass = password.text.toString()
        // val uName = username.text.toString()
        if (Email.isBlank() || pass.isBlank()){
            Toast.makeText(requireActivity(),"Email and password can't be blank!!",Toast.LENGTH_SHORT).show()
//            return
        }else {
            auth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireActivity(), "Sign IN Successfully", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(requireActivity(), HomePage::class.java))
//                    finish()
                } else {
                    Toast.makeText(requireActivity(), "Sign IN Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}