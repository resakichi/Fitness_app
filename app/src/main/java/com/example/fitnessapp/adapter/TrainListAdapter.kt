package com.example.fitnessapp.adapter

import android.graphics.Rect
import android.os.Bundle
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
import com.example.fitnessapp.data.Train

class TrainListAdapter (private val trains: List<Train>): RecyclerView.Adapter<TrainListVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainListVH {
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_train_list, parent, false)
        return TrainListVH(itemView)
    }

    override fun getItemCount(): Int  = trains.size


    override fun onBindViewHolder(holder: TrainListVH, position: Int) {
        holder.title?.text = trains[position].title
        //holder.image?.setImageDrawable( holder.itemView.resources.getDrawable(trains[position].image))
        Glide
            .with(holder.itemView.getContext())
            .load(trains[position].image)
            .into(holder.image!!)

        holder.card?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", "hands")
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