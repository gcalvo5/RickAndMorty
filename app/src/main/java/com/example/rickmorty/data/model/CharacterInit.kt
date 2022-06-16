package com.example.rickmorty.data.model

import android.util.Log
import com.example.rickmorty.core.RetrofitMethods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class CharacterInit @Inject constructor(val retrofitMethods: RetrofitMethods) {
    var characterList = mutableListOf<Character>()
    suspend fun createCharacterList(){
        withContext(Dispatchers.IO){
            characterList = retrofitMethods.getAllCharacters().body()?.characters!!
            if (retrofitMethods.getAllCharacters().isSuccessful){
                Log.e("Retrofit", "Success")
            }
            else{
                Log.e("retrofit", retrofitMethods.getAllCharacters().errorBody().toString())
            }
        }
    }
    suspend fun createCharacterListByUrls(url:String){
        withContext(Dispatchers.IO){
            characterList.add(retrofitMethods.getCharactersByUrl(url).body()!!)
            if (retrofitMethods.getCharactersByUrl(url).isSuccessful){
                Log.e("Retrofit", "get Character by url: Success")
            }
            else{
                Log.e("retrofit","get Character by url: " + retrofitMethods.getCharactersByUrl(url).errorBody().toString())
            }
        }

    }
}