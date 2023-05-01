package com.example.ghumoo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FeedbackFragment : Fragment() {
    private lateinit var database : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val feed: View =  inflater.inflate(R.layout.fragment_feedback, container, false)
        val rateBar = feed.findViewById<RatingBar>(R.id.ratingBar)
        val feedback = feed.findViewById<EditText>(R.id.feedback)
        val subFeed = feed.findViewById<MaterialButton>(R.id.getFeedback)

        subFeed.setOnClickListener {

            val ratings = rateBar.rating.toString()
            val feedbackText = feedback.text.toString()
            val uid = FirebaseAuth.getInstance().currentUser!!.uid

            database = FirebaseDatabase.getInstance().getReference("Users")
            val dataClass = FeedbackDataClass(ratings,feedbackText)

            database.child(uid).setValue(dataClass).addOnSuccessListener {
                feedback.text.clear()
//                Toast.makeText(requireContext(),"saved successfully",Toast.LENGTH_SHORT).show()
                showDialog()

            }.addOnFailureListener {
                Toast.makeText(requireContext(),"failed",Toast.LENGTH_SHORT).show()
            }
        }

        return feed
    }
    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.feedback_success)
//        val copy = dialog.findViewById<CardView>(R.id.copyPromo)
//        copy.setOnClickListener {
//            val copyTxt = dialog.findViewById<TextView>(R.id.promoCode).text.toString()
//            Toast.makeText(requireContext(), "Promo code ' $copyTxt ' copied to Clipboard", Toast.LENGTH_SHORT).show()
//        }
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.CENTER)
    }
}