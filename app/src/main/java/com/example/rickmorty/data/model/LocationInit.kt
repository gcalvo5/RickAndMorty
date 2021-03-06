package com.example.rickmorty.data.model

import android.util.Log
import com.example.rickmorty.core.RetrofitMethods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
class LocationInit @Inject constructor(val retrofitMethods: RetrofitMethods) {
    var locationsList = mutableListOf<Location>()
    var locations = Locations(InfoLocation("","","",""), mutableListOf<Location>())
    suspend fun getLocationById(url:String){
        withContext(Dispatchers.IO){
            if (!url.equals("")){
                locations.locations.add(retrofitMethods.getLocation(url).body()!!)
                if (retrofitMethods.getLocation(url).isSuccessful){
                    Log.e("Retrofit", "getLocationById : Success")
                }
                else{
                    Log.e("retrofit", "getLocationById : "+retrofitMethods.getLocation(url).errorBody().toString())
                }
            }

        }

    }
    suspend fun getAllLocations(){
        withContext(Dispatchers.IO){
            locations = retrofitMethods.getAllLocations().body()!!
            locationsList.addAll(locations.locations)
            if (retrofitMethods.getAllLocations().isSuccessful){
                Log.e("Retrofit", "getAllLocations : Success")
            }
            else{
                Log.e("retrofit", "getAllLocations : "+retrofitMethods.getAllLocations().errorBody().toString())
            }
        }

    }
    suspend fun getAllLocationsNextPageById(url: String){
        withContext(Dispatchers.IO){
            locations = retrofitMethods.getAllLocationsNextPageByUrl(url).body()!!
            locationsList.addAll(locations.locations)
            if (retrofitMethods.getAllLocationsNextPageByUrl(url).isSuccessful){
                Log.e("Retrofit", "getAllLocationsNextPageById : Success")
            }
            else{
                Log.e("retrofit", "getAllLocationsNextPageById : "+retrofitMethods.getAllLocationsNextPageByUrl(url).errorBody().toString())
            }
        }

    }
}