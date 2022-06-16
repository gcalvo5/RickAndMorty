package com.example.rickmorty.data.model

import android.util.Log
import com.example.rickmorty.core.RetrofitMethods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


class EpisodeInit @Inject constructor(val retrofitMethods: RetrofitMethods) {

    var episodes:Episodes = Episodes(InfoEpisode("","","","") ,mutableListOf<Episode>())
    suspend fun getEpisodeUrl(url:String) {
        withContext(Dispatchers.IO) {
            episodes.episodes.add(retrofitMethods.getEpisode(url).body()!!)
            if (retrofitMethods.getEpisode(url).isSuccessful){
                Log.e("Retrofit", "getEpisodeByUrl : Success")
            }
            else{
                Log.e("retrofit", "getEpisodeByUrl : "+retrofitMethods.getEpisode(url).errorBody().toString())
            }
        }
    }
    suspend fun getAllEpisodes() {
        withContext(Dispatchers.IO) {
            episodes = retrofitMethods.getAllEpisodes().body()!!
            if (retrofitMethods.getAllEpisodes().isSuccessful){
                Log.e("Retrofit", "getAllEpisodes : Success")
            }
            else{
                Log.e("retrofit", "getAllEpisodes : "+retrofitMethods.getAllEpisodes().errorBody().toString())
            }
        }
    }
}