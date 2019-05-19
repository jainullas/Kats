package com.jain.ullas.cats.repository

import com.jain.ullas.cats.BuildConfig

class CatRepository (private val api: CatApi) {
    fun getRandomCat() = api.randomCat(BuildConfig.API_KEY)
}