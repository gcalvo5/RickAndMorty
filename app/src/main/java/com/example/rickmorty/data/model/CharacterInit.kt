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
    var characters:Characters = Characters(Info("","","",""), mutableListOf())
    suspend fun createCharacterList(){
        withContext(Dispatchers.IO){
            characters = retrofitMethods.getAllCharacters().body()!!
            characterList.addAll(characters.characters)

            if (retrofitMethods.getAllCharacters().isSuccessful){
                Log.e("Retrofit", "Success")
            }
            else{
                Log.e("retrofit", retrofitMethods.getAllCharacters().errorBody().toString())
            }
        }
    }
    suspend fun getCharactersNextPageByUrl(url: String){
        withContext(Dispatchers.IO){


            if (retrofitMethods.getAllCharactersNextPageByUrl(url).isSuccessful){
                Log.e("Retrofit", "get Character Next Page by url: Success")
            }
            else{
                Log.e("retrofit","get Character Next Page by url: " + retrofitMethods.getAllCharactersNextPageByUrl(url).errorBody().toString())
            }
            characters = retrofitMethods.getAllCharactersNextPageByUrl(url).body()!!
            characterList.addAll(characters.characters)
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