package com.example.rickmorty.data.model

import com.google.gson.annotations.SerializedName

data class Locations(
    @SerializedName("info") val info:InfoLocation,
    @SerializedName("results") val locations:MutableList<Location>
)
data class Location(
    @SerializedName("id")val id:String,
    @SerializedName("name")val name:String?,
    @SerializedName("type")val type:String?,
    @SerializedName("dimension")val dimension:String?,
    @SerializedName("residents")val residents: MutableList<String>,
    @SerializedName("url")val url:String?,
    @SerializedName("created")val creation:String?
)
data class InfoLocation(
    @SerializedName("count")val count:String,
    @SerializedName("pages")val pages:String,
    @SerializedName("next")val next:String,
    @SerializedName("prev")val prev:String
)