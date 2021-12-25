package com.example.thirdparty_ghh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    //https://api.rawg.io/api/games?key=f317aaea33b94f7f94ef9ab3ef8ab2dc&dates=2020-01-01,2021-12-30&platforms=18,1,7

    private var retrofitVar: Retrofit? = null


    fun request(): Retrofit? {
        retrofitVar = Retrofit.Builder().baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitVar
    }
}