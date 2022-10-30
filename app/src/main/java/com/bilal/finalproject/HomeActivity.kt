package com.bilal.finalproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bilal.finalproject.Api.ApiConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.abs


class HomeActivity : AppCompatActivity() {

    private lateinit var userEmail: String
    private lateinit var auth: FirebaseAuth
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        get data user
//        userEmail = getEmailFromPreference()
//
//        findViewById<TextView>(R.id.tv_emai).apply {
//            text = "user ${userEmail}"
//        }
//
//        logout
        val btnLogout = findViewById<ImageView>(R.id.iv_profile)

        btnLogout.setOnClickListener {
            logout()
        }
        viewPager2 = findViewById(R.id.viewPager_ImageSlider)

        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.drawable.slider1))
        sliderItems.add(SliderItem(R.drawable.slider2))
        sliderItems.add(SliderItem(R.drawable.slider3))
        sliderItems.add(SliderItem(R.drawable.slider4))
        sliderItems.add(SliderItem(R.drawable.slider5))
        sliderItems.add(SliderItem(R.drawable.slider6))
        sliderItems.add(SliderItem(R.drawable.slider7))

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = true
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val screenWidth = resources.displayMetrics.widthPixels

        val compositePageTransformer = CompositePageTransformer()
//        get position slider
        compositePageTransformer.addTransformer { page, position ->
            Log.d("POS", "position:${position}; page:${page.id}")
            page.translationX = ((screenWidth / 2f) * -(position))

            val r = 1 - abs(position)
//            ukuran
            page.scaleY = 0.85f + r * 0.25f - 0.1f
            page.scaleX = 0.85f + r * 0.25f - 0.1f
        }

        viewPager2.setPageTransformer(compositePageTransformer)

//        btn language
        val btnLanguage = findViewById<ImageView>(R.id.iv_language)
        btnLanguage.setOnClickListener {
            startActivityForResult(Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS), 0);
        }


//        get data
        val movie = findViewById<RecyclerView>(R.id.rv_movie)

        ApiConfig.getService().getMovie("473e71363d36bdfab0a8cc7bb116b67e").enqueue(object : Callback<ResponseMovie>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                if (response.isSuccessful) {
                    val responseMovie = response.body()
                    val dataMovie = responseMovie?.results
                    val movieAdapter = MovieAdapter(dataMovie as List<ResultsItem>){}
                    movie.apply {
                        layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                        movieAdapter.notifyDataSetChanged()
                        adapter = movieAdapter

                    }
                }else{
                    Toast.makeText(this@HomeActivity, "gagal", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun getEmailFromPreference(): String {
        val shaderPref = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        return shaderPref.getString("EMAIL", "underfined")!!
    }

    private fun logout() {
        Firebase.auth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish() // destroy activity sebelumnya agar gak bisa balik ke activity sebelumnya
    }


}