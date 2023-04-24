package com.example.ghumoo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class HomeFragment : Fragment() {
    lateinit var profileCard : CardView
    lateinit var profileImage : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_home, container, false)

        profileCard = v.findViewById(R.id.profileCardView)
        profileCard.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,ProfileFragment())
                transaction.commit()
        }
        return v
    }
}