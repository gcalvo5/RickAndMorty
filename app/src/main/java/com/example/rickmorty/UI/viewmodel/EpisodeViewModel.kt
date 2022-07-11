package com.example.rickmorty.UI.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.data.model.Episode
import com.example.rickmorty.data.model.EpisodeInit
import com.example.rickmorty.data.model.Episodes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(var episodeInit: EpisodeInit) : ViewModel(){
    var episodeViewModel = MutableLiveData<Episodes>(episodeInit.episodes)
    var episodes:Episodes = episodeInit.episodes
    var episodeListViewModel = MutableLiveData<MutableList<Episode>>(episodeInit.episodeList)
    suspend fun callForEpisodesByUrls(urls:ArrayList<String>){
        for (i in urls){
            episodeInit.getEpisodeUrl(i)
        }
        episodeViewModel.postValue(episodeInit.episodes)

    }
    suspend fun callForAllEpisodes(){

        episodeInit.getAllEpisodes()

        episodeViewModel.postValue(episodeInit.episodes)
        episodes = episodeInit.episodes
        episodeListViewModel.postValue(episodeInit.episodeList)
    }
    suspend fun callForAllEpisodesNextPageByUrl(url:String){

        episodeInit.getAllEpisodesnextPageByUrl(url)

        episodeViewModel.postValue(episodeInit.episodes)
        episodes = episodeInit.episodes
        episodeListViewModel.postValue(episodeInit.episodeList)
    }
}