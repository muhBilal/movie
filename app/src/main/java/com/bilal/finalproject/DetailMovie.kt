package com.bilal.finalproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

class DetailMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

//        Toast.makeText(this, "${movie}", Toast.LENGTH_SHORT).show()
        val b = intent.extras
        val movie = b?.getParcelable<ResultsItem>("movie")

//        Toast.makeText(this, movie?.title, Toast.LENGTH_SHORT).show()

        val detailTitle = findViewById(R.id.tv_detailTIttle) as TextView
        val detailDesc = findViewById(R.id.tv_detailDesc) as TextView
        val detailCover = findViewById<ImageView>(R.id.iv_detailCover)
        val buttonback = findViewById<ImageView>(R.id.iv_back)
        val btnWatch = findViewById<Button>(R.id.btn_watch)
        val btnShare = findViewById<ImageView>(R.id.iv_share)
        val rate = findViewById<TextView>(R.id.rate)

        detailTitle.text = movie?.title
        detailDesc.text = movie?.overview
        rate.text = movie?.voteAverage.toString()

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie?.posterPath}").into(detailCover)

        buttonback.setOnClickListener {
            onBackPressed()
        }

        btnWatch.setOnClickListener {
            val Intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/results?search_query=" + movie?.title)
            )
            startActivity(Intent)
        }

        btnShare.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${movie?.title}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
    }
}