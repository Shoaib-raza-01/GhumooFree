package com.example.ghumoo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterClass(private val placeList: ArrayList<DataModel>): RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeImage: ImageView = itemView.findViewById(R.id.placeImage)
        val placeName: TextView = itemView.findViewById(R.id.placeName)
        val location : TextView = itemView.findViewById(R.id.location)
        val about : TextView = itemView.findViewById(R.id.aboutPlace)
        val rating : TextView = itemView.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.places_firestore, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.placeName.text = placeList[position].name
        holder.location.text = placeList[position].location
        holder.about.text = placeList[position].about
        holder.rating.text = placeList[position].rating
        val imageUri = placeList[position].imageUrl
//        Glide.with(Activity())
//            .load(imageUri)
//            .into(holder.placeImage)
        Picasso.get().load(imageUri).into(holder.placeImage)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}
