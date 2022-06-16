package com.example.rickmorty.data.model

import com.google.gson.annotations.SerializedName
data class Episodes(
    @SerializedName("info") val info: InfoEpisode,
    @SerializedName("results") val episodes:MutableList<Episode>
)
data class Episode(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("air_date") val air_date:String,
    @SerializedName("episode") val episode:String,
    @SerializedName("characters") val characters: MutableList<String>,
    @SerializedName("url") val url:String,
    @SerializedName("created") val created:String
)
data class InfoEpisode(
    val count:String,
    val pages:String,
    val next:String,
    val prev:String
)