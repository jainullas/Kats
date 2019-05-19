package com.jain.ullas.cats.repository

import com.jain.ullas.cats.data.RandomCat
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header

interface CatApi {

    @GET("images/search")
    fun randomCat(@Header("x-api-key") apiKey : String) : Flowable<List<RandomCat>>

}