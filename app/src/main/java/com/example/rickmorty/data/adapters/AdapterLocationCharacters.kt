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
import com.example.rickmorty.data.model.Character

import com.example.rickmorty.data.model.Episode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterLocationCharacters @Inject constructor(private val characterList: List<Character>): RecyclerView.Adapter<AdapterLocationCharacters.ViewHolder>() {

    lateinit var navController: NavController

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameText:TextView
        val locationLayout:View
        init {
            nameText = itemView.findViewById(R.id.locationRecyclerCharacterName)
            locationLayout = itemView.findViewById(R.id.locationRecyclerLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.items_location,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = characterList[position].name
        holder.locationLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                navController = Navigation.findNavController(view!!)

                var bundle = Bundle()
                bundle.putString("id", characterList[holder.adapterPosition].id)
                bundle.putString("name", characterList[holder.adapterPosition].name)
                bundle.putString("status", characterList[holder.adapterPosition].status)
                bundle.putString("species", characterList[holder.adapterPosition].species)
                bundle.putString("type", characterList[holder.adapterPosition].type)
                bundle.putString("gender", characterList[holder.adapterPosition].gender)
                bundle.putString("originUrl", characterList[holder.adapterPosition].origin.url)
                bundle.putString("image", characterList[holder.adapterPosition].image)
                bundle.putStringArrayList("episodesUrls", characterList[holder.adapterPosition].episode)

                navController.navigate(R.id.nav_character, bundle)
            }

        })
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


}