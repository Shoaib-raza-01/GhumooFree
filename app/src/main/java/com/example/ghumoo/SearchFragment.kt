package com.example.ghumoo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment() {
    private lateinit var recyclView :RecyclerView
    private lateinit var activityListArray : ArrayList<ActivityModel>
    private lateinit var  dataBase : FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v : View =  inflater.inflate(R.layout.fragment_search, container, false)

        recyclView = v.findViewById(R.id.activityRecyclerView)
        recyclView.layoutManager = LinearLayoutManager(requireContext())
        activityListArray = arrayListOf()

        dataBase = FirebaseFirestore.getInstance()
        dataBase.collection("Activities").get()
            .addOnSuccessListener{
                if(!it.isEmpty){
                    for(act in it.documents){
                        val activityList : ActivityModel? = act.toObject(ActivityModel::class.java)
                        if(activityList != null){
                            activityListArray.add(activityList)
                        }
                    }
                    recyclView.adapter = ActivityAdapter(activityListArray)
                }
            }.addOnFailureListener{
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }
        return  v
    }

}