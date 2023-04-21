package com.example.ghumoo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    lateinit var signOut : Button
    lateinit var uid :TextView
    private lateinit var auth :FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_profile, container, false)
        signOut = v.findViewById(R.id.signOutBtn)
        uid = v.findViewById(R.id.Uid)

        //initializing the firebase auth config
        auth = Firebase.auth

        //getting the current user id
        uid.text = auth.currentUser!!.uid

        //signing out the user
        signOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireActivity(),MainActivity::class.java))
        }
        return v
    }
}