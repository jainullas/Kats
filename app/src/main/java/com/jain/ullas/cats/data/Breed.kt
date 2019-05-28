package com.jain.ullas.cats.data

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("wikipedia_url") val wikipediaUrl: String?
)