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
import com.example.rickmorty.data.model.Location
import java.util.ArrayList
import javax.inject.Inject

class AdapterLocations @Inject constructor(private val locationsList:List<Location>):RecyclerView.Adapter<AdapterLocations.ViewHolder>() {
    lateinit var navController: NavController
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameText: TextView
        var dimensionText:TextView
        var layout:View
        init {
            nameText = itemView.findViewById(R.id.locationsRecyclerName)
            dimensionText = itemView.findViewById(R.id.locationsRecyclerDimensions)
            layout = itemView.findViewById(R.id.itemLocationsLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.items_locations,parent,false)
        return AdapterLocations.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = locationsList.get(position).name
        holder.dimensionText.text = locationsList.get(position).dimension

        holder.layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                navController = Navigation.findNavController(view!!)

                var bundle = Bundle()
                bundle.putString("name", locationsList[holder.adapterPosition].name)
                bundle.putString("type", locationsList[holder.adapterPosition].type)
                bundle.putString("dimension", locationsList[holder.adapterPosition].dimension)
                bundle.putStringArray("residents", locationsList[holder.adapterPosition].residents.toTypedArray())

                navController.navigate(R.id.nav_location, bundle)
            }

        })

    }

    override fun getItemCount(): Int {
        return locationsList.size
    }


}