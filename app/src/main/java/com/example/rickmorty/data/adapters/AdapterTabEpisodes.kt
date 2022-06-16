package com.example.rickmorty.data.adapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R

import com.example.rickmorty.data.model.Episode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterTabEpisodes @Inject constructor(private val episodeList: List<Episode>): RecyclerView.Adapter<AdapterTabEpisodes.ViewHolder>() {

    lateinit var navController: NavController

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameText:TextView
        var episodeTabLayout:View
        init {
            nameText = itemView.findViewById(R.id.tabEpisodesRecyclerName)
            episodeTabLayout = itemView.findViewById(R.id.episodeTabLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.items_tab_episodes,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = episodeList[position].name
        holder.episodeTabLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                navController = Navigation.findNavController(view!!)

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