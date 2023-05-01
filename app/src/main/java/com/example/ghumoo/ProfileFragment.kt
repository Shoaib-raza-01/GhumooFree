package com.example.ghumoo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var auth :FirebaseAuth
    private lateinit var userPic: ImageView
    private lateinit var userName : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v : View =  inflater.inflate(R.layout.fragment_profile, container, false)
        userPic = v.findViewById(R.id.userProfileImage)
        userName = v.findViewById(R.id.userName)
        auth = FirebaseAuth.getInstance()
        Picasso.get().load(auth.currentUser!!.photoUrl).into(userPic)
        userName.text = auth.currentUser!!.displayName

        val logOut = v.findViewById<androidx.cardview.widget.CardView>(R.id.logOut)
        logOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }

        val creditCoupons = v.findViewById<androidx.cardview.widget.CardView>(R.id.credit_coupons)
        creditCoupons.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,CreditCouponFragment()).addToBackStack(null).commit()
        }

        val writeToUs = v.findViewById<CardView>(R.id.writeToUs)
        writeToUs.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,FeedbackFragment()).addToBackStack(null).commit()
        }

        return v
    }
}