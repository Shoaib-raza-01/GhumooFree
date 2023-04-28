package com.example.ghumoo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ActivityAdapter(private val activityList : ArrayList<ActivityModel>): RecyclerView.Adapter<ActivityAdapter.MyActivityHolder>() {

    class MyActivityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityImage: ImageView = itemView.findViewById(R.id.activityImage)
        val activityName: TextView = itemView.findViewById(R.id.actName)
        val count : TextView = itemView.findViewById(R.id.count)
        val personOne : ImageView = itemView.findViewById(R.id.perOne)
        val personTwo : ImageView = itemView.findViewById(R.id.perTwo)
        val activityRating : TextView = itemView.findViewById(R.id.actRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivityHolder {
        val act = LayoutInflater.from(parent.context).inflate(R.layout.popular_activities,parent, false)
        return  MyActivityHolder(act)
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    override fun onBindViewHolder(holder: MyActivityHolder, position: Int) {
        holder.activityName.text = activityList[position].actName
        holder.count.text = activityList[position].count
        holder.activityRating.text = activityList[position].actRating

        val actUri = activityList[position].actImgUrl
        Picasso.get().load(actUri).into(holder.activityImage)

        val p1Uri = activityList[position].perOne
        Picasso.get().load(p1Uri).into(holder.personOne)

        val p2Uri = activityList[position].perTwo
        Picasso.get().load(p2Uri).into(holder.personTwo)
    }
}