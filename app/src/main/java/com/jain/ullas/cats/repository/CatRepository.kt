package com.jain.ullas.cats.repository

class CatRepository (private val api: CatApi) {
    fun getRandomCat() = api.randomCat()
}