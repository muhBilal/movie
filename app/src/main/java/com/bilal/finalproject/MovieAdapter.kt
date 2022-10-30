package com.bilal.finalproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val dataMovie: List<ResultsItem>,
    val eventHandler: (ResultsItem) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgmovie = view.findViewById<ImageView>(R.id.iv_cover)
        val title = view.findViewById<TextView>(R.id.tv_tittle)
//        val desc = view.findViewById<TextView>(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_layout_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataMovie[position].title
//        holder.desc.text = dataMovie?.get(position)?.overview

        Glide.with(holder.imgmovie)
            .load("https://image.tmdb.org/t/p/w500/${dataMovie[position].posterPath}")
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.imgmovie)

        holder.itemView.setOnClickListener {
//            val data = dataMovie[position]
//            Toast.makeText(holder.itemView.context, "${data}", Toast.LENGTH_SHORT).show()
            it.context.startActivity(Intent(it.context, DetailMovie::class.java).putExtra("movie", dataMovie[position]))
        }
//        holder.itemView.setOnClickListener { eventHandler(dataMovie[position])}

    }


    override fun getItemCount(): Int {
        return dataMovie.size
    }
}
