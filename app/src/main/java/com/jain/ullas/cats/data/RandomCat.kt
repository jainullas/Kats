package com.jain.ullas.cats.data

import com.google.gson.annotations.SerializedName

data class RandomCat(
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: Int = 0,
    @SerializedName("height") val height: Int = 0
)