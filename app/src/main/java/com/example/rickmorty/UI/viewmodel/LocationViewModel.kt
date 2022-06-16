package com.example.rickmorty.UI.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.data.model.Location
import com.example.rickmorty.data.model.LocationInit
import com.example.rickmorty.data.model.Locations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(var locationInit: LocationInit): ViewModel() {
    val locationViewModel = MutableLiveData<Locations>(locationInit.locations)

    suspend fun callForLocationByUrl(url:String){
        locationInit.getLocationById(url)
        locationViewModel.postValue(locationInit.locations)
    }
    suspend fun callForAllLocations(){
        locationInit.getAllLocations()
        locationViewModel.postValue(locationInit.locations)
    }
}