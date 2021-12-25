package com.example.thirdparty_ghh

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thirdparty_ghh.Model.Data
import com.example.thirdparty_ghh.Model.Genre
import com.example.thirdparty_ghh.Model.PlatformX
import com.example.thirdparty_ghh.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listOfGame: ArrayList<Games>
    private lateinit var listOfGameOld: ArrayList<Games>
    private lateinit var listOfGameNew: ArrayList<Games>

    private lateinit var myAdapter: AdapterRow
    private lateinit var myAdapterOld: AdapterRow
    private lateinit var myAdapterNew: AdapterRow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.hide()
        binding.imageView3.setBackgroundResource(R.mipmap.all)

        setAdapters()
        binding.NSidedProgressBar.baseSpeed = 5F
        fetch()
    }

    private fun setAdapters(){
        listOfGame = arrayListOf()
        listOfGameOld = arrayListOf()
        listOfGameNew = arrayListOf()

        myAdapter = AdapterRow(listOfGame,this)
        binding.mainRV3All.adapter = myAdapter
        binding.mainRV3All.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)

        myAdapterOld = AdapterRow(listOfGameOld,this)
        binding.mainRV2Old.adapter = myAdapterOld
        binding.mainRV2Old.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)

        myAdapterNew = AdapterRow(listOfGameNew,this)
        binding.mainRVNew.adapter = myAdapterNew
        binding.mainRVNew.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)

    }
    private fun fetch(){
        var api = Client().request()?.create(API::class.java)

        api?.get()?.enqueue(object : Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
               var result = response.body()!!.results
                Log.d("Main","something ${response.isSuccessful}")

                listOfGameNew.clear()
                listOfGame.clear()
                listOfGameOld.clear()
                for (item in result){
                    when {
                        item.released < "2020-01-01" -> {
                            listOfGameOld.add(Games(item.name,item.background_image,item.rating.toString(),
                                item.rating_top.toString(),item.released,
                                item.genres as ArrayList<Genre>,item.playtime, item.platforms as ArrayList<PlatformX>
                            ))
                        }
                        item.released > "2020-01-01" -> {
                            listOfGameNew.add(Games(item.name,item.background_image,item.rating.toString(),
                                item.rating_top.toString(),item.released,
                                item.genres as ArrayList<Genre>,item.playtime, item.platforms as ArrayList<PlatformX>
                            ))
                        }
                        else -> {}

                    }
                    listOfGame.add(Games(item.name,item.background_image,item.rating.toString(),
                        item.rating_top.toString(),item.released,
                        item.genres as ArrayList<Genre>,item.playtime, item.platforms as ArrayList<PlatformX>
                    ))

                }
                myAdapter.updateRecyclerView(listOfGame)
                myAdapterOld.updateRecyclerView(listOfGameOld)
                myAdapterNew.updateRecyclerView(listOfGameNew)
                binding.NSidedProgressBar.isDeterminate = true
                binding.NSidedProgressBar.visibility = View.GONE



            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("Main","something went wrong ${t.localizedMessage}")
            }
        })
    }
    fun toDetails(theGame:Games){

        val toDetailsActivity = Intent(this, DetailsActivity::class.java)
        toDetailsActivity.putExtra("gameName" , theGame.name)
        toDetailsActivity.putExtra("gameRating" , theGame.rating)
        toDetailsActivity.putExtra("gameToRating" , theGame.TopRating)
        toDetailsActivity.putExtra("gameUrl" , theGame.URL)
        toDetailsActivity.putExtra("gameDate" ,theGame.date)
        var genres = theGame.genres
        var allGenres = ""
        for (genre in genres){
            allGenres += "\n ${genre.name}"
        }
        toDetailsActivity.putExtra("gameGenre" ,allGenres)
        toDetailsActivity.putExtra("gameTime" ,theGame.playtime)
        var platforms = theGame.platforms
        var allPlatforms = ""
        for (platform in platforms){
            allPlatforms += "\n ${platform.platform.name}"
        }
        toDetailsActivity.putExtra("gameForm" ,allPlatforms)

        startActivity(toDetailsActivity)

    }
}