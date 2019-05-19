package com.jain.ullas.cats.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jain.ullas.cats.common.AbstractViewModel
import com.jain.ullas.cats.data.RandomCat
import com.jain.ullas.cats.usecases.GetRandomCatUseCase

class MainViewModel(private val repository: GetRandomCatUseCase) : AbstractViewModel() {
    private val TAG = MainViewModel::class.java.simpleName

    val uiData = MutableLiveData<RandomCatViewModel>()

    internal fun loadRandomCat() {
        launch {
            uiData.value = RandomCatViewModel(true)
            repository.getRandomCat()
                .subscribe({ randomCat ->
                    if (randomCat.url?.isNotBlank() == true) {
                        uiData.value = RandomCatViewModel(false, randomCat)
                    }
                }, {
                    uiData.value = RandomCatViewModel(false, null)
                    Log.d(TAG, "", it)

                })
        }
    }

}

data class RandomCatViewModel(val isLoading: Boolean = false, val randomCat: RandomCat? = null)