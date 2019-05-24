package com.jain.ullas.cats.repository
import com.jain.ullas.cats.data.RandomCat
import io.reactivex.Flowable
import retrofit2.http.GET

interface CatApi {

    @GET("images/search")
    fun randomCat() : Flowable<List<RandomCat>>

}