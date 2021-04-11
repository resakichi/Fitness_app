package com.example.fitnessapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Article
import com.squareup.picasso.Picasso

class ArticleListAdapter (val articles: List<Article>) :
        RecyclerView.Adapter<ArticleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)

        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        holder.category?.text = articles[position].category
        holder.title?.text = articles[position].title

        Picasso.get()
            .load(articles[position].image)
            .into(holder.image)

        holder.readBtn?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("image", articles[position].image)
            bundle.putString("description", articles[position].description)
            bundle.putString("title", articles[position].title)
            holder.itemView.findNavController().navigate(R.id.articleInfoFragment, bundle)
        }
    }


}

class  ArticleViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    var image: ImageView? = null
    var category: TextView? = null
    var title: TextView? = null
    var readBtn: Button? = null

    init {
        image = itemView.findViewById(R.id.article_image)
        category = itemView.findViewById(R.id.article_category)
        title = itemView.findViewById(R.id.article_title)
        readBtn = itemView.findViewById(R.id.read_btn)
        }
}