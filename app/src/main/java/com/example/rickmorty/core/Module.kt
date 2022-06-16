package com.example.rickmorty.core

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.rickmorty.R
import com.example.rickmorty.UI.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun ProvideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Singleton
    @Provides
    fun ProvideRetrofitMethods(retrofit: Retrofit):RetrofitMethods{
        return retrofit.create(RetrofitMethods::class.java)
    }
}