package com.example.ghumoo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    lateinit var profileCard : CardView
    lateinit var profileImage : ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var placeArrayList: ArrayList<DataModel>
    private lateinit var db : FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_home, container, false)
        profileCard = v.findViewById(R.id.profileCardView)

        recyclerView = v.findViewById(R.id.placesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        placeArrayList = arrayListOf()

        //to set the profile picture of user
        profileImage = v.findViewById(R.id.circularProfilePic)
        val pic = FirebaseAuth.getInstance().currentUser!!.photoUrl
        if (pic != null){
            Glide.with(this).load(pic).into(profileImage)
        }else{
            Glide.with(this).load(R.drawable.baseline_person_24).into(profileImage)
        }

        //to move user from homeFragment to profileFragment
        profileCard.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,ProfileFragment())
            transaction.commit()
        }

        //database firestore
        db = FirebaseFirestore.getInstance()

        db.collection("Places")
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    for ( data in it.documents){
                        var Data : DataModel? = data.toObject(DataModel::class.java)
                        if (Data != null) {
                            placeArrayList.add(Data)
                        }
                    }
                    recyclerView.adapter = AdapterClass(placeArrayList)
                }
            }.addOnFailureListener{
                Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }

        return v
    }

}