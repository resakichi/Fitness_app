package com.example.fitnessapp.adapter

import android.graphics.Rect
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
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Exercises
import com.example.fitnessapp.data.Train
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class TrainListAdapter (private val exercises: List<Train>): RecyclerView.Adapter<TrainListVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainListVH {
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_train_list, parent, false)
        return TrainListVH(itemView)
    }

    override fun getItemCount(): Int  = exercises.size


    override fun onBindViewHolder(holder: TrainListVH, position: Int) {
        holder.title?.text = exercises[position].name

        Picasso.get()
            .load(exercises[position].image)
            .into(holder.image)

        holder.card?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", exercises[position].id)
            bundle.putString("name", exercises[position].name)
            holder.itemView.findNavController().navigate(R.id.trainExerciseList, bundle)
        }
    }
}

class TrainListVH (itemView: View) : RecyclerView.ViewHolder (itemView){
    var title: TextView? = null
    var image: ImageView? = null
    var card: CardView? = null

    init {
        title = itemView.findViewById(R.id.train_title)
        image = itemView.findViewById(R.id.train_image)
        card = itemView.findViewById(R.id.train_card)

            }
}

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = verticalSpaceHeight
    }

}