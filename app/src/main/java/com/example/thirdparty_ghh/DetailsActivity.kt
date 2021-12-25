package com.example.thirdparty_ghh

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.thirdparty_ghh.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnBack.setOnClickListener {
            val toMain = Intent(this,MainActivity::class.java)
            startActivity(toMain)
        }
        setData()
    }

    @SuppressLint("SetTextI18n")
    private fun setData(){
        binding.apply {
            Glide.with(this@DetailsActivity).load(intent.getStringExtra("gameUrl")).into(ivBig)
            Glide.with(this@DetailsActivity).load(intent.getStringExtra("gameUrl")).into(ivPoster)
            tvName.text = intent.getStringExtra("gameName")
            tvDate.text = intent.getStringExtra("gameDate")
            tvPlatTime.text = "${intent.getIntExtra("gameTime",0)} Hours"
            tvRating.text = "${intent.getStringExtra("gameRating")} / ${intent.getStringExtra("gameToRating")}"
            tvGenre.text = intent.getStringExtra("gameGenre")
            tvPlatform.text = intent.getStringExtra("gameForm")

            RatingBar.numberOfStars = intent.getStringExtra("gameToRating")!!.toInt()
            RatingBar.rating = intent.getStringExtra("gameRating")!!.toFloat()
            RatingBar.isIndicator = true

        }


    }
}