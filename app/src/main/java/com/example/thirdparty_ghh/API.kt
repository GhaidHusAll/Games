package com.example.thirdparty_ghh

import com.example.thirdparty_ghh.Model.Data
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface API {

    @GET("games?key=f317aaea33b94f7f94ef9ab3ef8ab2dc&dates=2019-01-01,2022-12-30&platforms=18,1,7")
    fun get(): Call<Data>
}