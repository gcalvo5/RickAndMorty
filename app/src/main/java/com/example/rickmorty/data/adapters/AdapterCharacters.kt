package com.example.rickmorty.data.adapters

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.UI.view.Hilt_MainActivity
import com.example.rickmorty.data.model.Character
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdapterCharacters @Inject constructor(private val characterList: List<Character>): RecyclerView.Adapter<AdapterCharacters.ViewHolder>() {
    lateinit var navController:NavController



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var characterName:TextView
        var charecterImage:ImageView
        var characterLayout:View
        init {
            characterName = itemView.findViewById(R.id.nameCharacter)
            charecterImage = itemView.findViewById(R.id.imageCharacter)
            characterLayout = itemView.findViewById(R.id.layoutCharacter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.items_characters,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.characterName.text = characterList[position].name
        Picasso.get().load(characterList[position].image).into(holder.charecterImage)
        holder.characterLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                navController = findNavController(view!!)

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