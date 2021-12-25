package com.example.thirdparty_ghh

import com.example.thirdparty_ghh.Model.Genre
import com.example.thirdparty_ghh.Model.PlatformX

data class Games(
    var name: String,
    var URL: String,
    var rating:String, var TopRating: String,
    var date: String, var genres: ArrayList<Genre>, var playtime: Int,
    var platforms: ArrayList<PlatformX>
)
