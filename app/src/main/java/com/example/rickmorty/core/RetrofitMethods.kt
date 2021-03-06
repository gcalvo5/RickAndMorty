package com.example.rickmorty.core

import com.example.rickmorty.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitMethods {
    @GET("https://rickandmortyapi.com/api/character/")
    suspend fun getAllCharacters(): Response<Characters>

    @GET
    suspend fun getAllCharactersNextPageByUrl(@Url url:String): Response<Characters>

    @GET
    suspend fun getAllEpisodesNextPageByUrl(@Url url:String): Response<Episodes>

    @GET
    suspend fun getAllLocationsNextPageByUrl(@Url url:String): Response<Locations>

    @GET("{url}/")
    suspend fun getLocation(@Path("url", encoded = true) url:String): Response<Location>

    @GET("{url}/")
    suspend fun getEpisode(@Path("url", encoded = true) url:String): Response<Episode>

    @GET("https://rickandmortyapi.com/api/episode/")
    suspend fun getAllEpisodes(): Response<Episodes>

    @GET("https://rickandmortyapi.com/api/location/")
    suspend fun getAllLocations(): Response<Locations>

    @GET("{url}/")
    suspend fun getCharactersByUrl(@Path("url", encoded = true) url:String): Response<Character>

}