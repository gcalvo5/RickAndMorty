package com.example.rickmorty.data.model

import com.google.gson.annotations.SerializedName
data class Characters(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val characters: MutableList<Character>
)

data class Character(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("status") val status:String,
    @SerializedName("species") val species:String,
    @SerializedName("type") val type:String,
    @SerializedName("gender") val gender:String,
    @SerializedName("origin") val origin: Origin,
    @SerializedName("location") val location: Origin,
    @SerializedName("image") val image:String,
    @SerializedName("episode") val episode: ArrayList<String>,
    @SerializedName("url") val url:String,
    @SerializedName("created") val created:String
)
data class Origin(@SerializedName("name") val name:String, @SerializedName("url") val url:String)
data class Info(
    @SerializedName("count") val count:String,
    @SerializedName("pages") val pages:String,
    @SerializedName("next") val next:String?,
    @SerializedName("prev") val prev:String?
    )