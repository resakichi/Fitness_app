package com.example.fitnessapp.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Exercises
import com.squareup.picasso.Picasso

class ExerciseListAdapter (val items: List<Exercises>): RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)

        return ExerciseViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {

        holder.text.text = items[position].name
        Picasso.get()
            .load(items[position].image)
            .into(holder.image)

        holder.card.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("name", items[position].name)
            bundle.putLong("repeats", items[position].repeats)
            bundle.putLong("approaches", items[position].approaches)
            bundle.putString("image", items[position].exerciseImage)
            bundle.putString("description", items[position].description)
            bundle.putString("rest", items[position].rest)
            holder.itemView.findNavController().navigate(R.id.exerciseInfoFragment, bundle)
        }


    }
}

class ExerciseViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){

    var card: CardView = itemView.findViewById(R.id.exercise_card)
    var image: ImageView = itemView.findViewById(R.id.exercise_image)
    val text: TextView = itemView.findViewById(R.id.exercise_text)


}