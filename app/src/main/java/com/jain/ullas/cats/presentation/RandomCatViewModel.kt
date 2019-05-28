package com.jain.ullas.cats.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jain.ullas.cats.common.AbstractViewModel
import com.jain.ullas.cats.data.RandomCat
import com.jain.ullas.cats.usecases.GetRandomCatUseCase

class RandomCatViewModel(private val repository: GetRandomCatUseCase) : AbstractViewModel() {
    private val TAG = RandomCatViewModel::class.java.simpleName

    val uiData = MutableLiveData<RandomCatViewModelData>()

    internal fun loadRandomCat() {
        launch {
            uiData.value = RandomCatViewModelData(true)
            repository.getRandomCat()
                .subscribe({ randomCat ->
                    if (randomCat.url?.isNotBlank() == true) {
                        uiData.value = RandomCatViewModelData(false, randomCat)
                    }
                }, {
                    uiData.value = RandomCatViewModelData(false, null)
                    Log.d(TAG, "", it)

                })
        }
    }

}

data class RandomCatViewModelData(val isLoading: Boolean = false, val randomCat: RandomCat? = null)