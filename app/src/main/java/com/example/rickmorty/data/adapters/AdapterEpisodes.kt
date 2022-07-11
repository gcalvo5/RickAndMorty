package com.example.rickmorty.data.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController

import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R

import com.example.rickmorty.data.model.Episode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterEpisodes @Inject constructor(private val episodeList: List<Episode>): RecyclerView.Adapter<AdapterEpisodes.ViewHolder>() {

    lateinit var navController: NavController

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameText:TextView
        var episodeText:TextView
        var episodeLayout:View
        init {
            nameText = itemView.findViewById(R.id.episodesRecyclerName)
            episodeText = itemView.findViewById(R.id.episodesRecyclerEpisode)
            episodeLayout = itemView.findViewById(R.id.episodesRecyclerLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.items_episodes,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = episodeList[position].name
        holder.episodeText.text = episodeList[position].episode
        holder.episodeLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                navController = findNavController(view!!)

                var bundle = Bundle()

                bundle.putString("episodeName", episodeList[holder.adapterPosition].name)
                bundle.putString("airDate", episodeList[holder.adapterPosition].air_date)
                bundle.putString("episodeEpisode", episodeList[holder.adapterPosition].episode)
                var charactersArrayList:Array<String> = episodeList[holder.adapterPosition].characters.toTypedArray()
                bundle.putStringArray("characters", charactersArrayList)
                navController.navigate(R.id.nav_episode, bundle)
            }

        })

    }

    override fun getItemCount(): Int {
        return episodeList.size
    }


}