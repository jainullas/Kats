package com.jain.ullas.cats.presentation.breeds

import com.jain.ullas.cats.data.Breed

interface BreedsView {

    fun showBreeds(breeds: List<Breed>)

    fun hideBreeds()

    fun showProgress()

    fun hideProgress()

}