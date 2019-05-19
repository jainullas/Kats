package com.jain.ullas.cats.presentation

interface MainView {

    fun showRandomCat(url: String)

    fun hideRandomCat()

    fun onRefreshButtonClicked()

    fun showProgress()

    fun hideProgress()

}