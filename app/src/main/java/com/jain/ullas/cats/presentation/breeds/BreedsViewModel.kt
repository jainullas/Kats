package com.jain.ullas.cats.presentation.breeds

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jain.ullas.cats.common.AbstractViewModel
import com.jain.ullas.cats.data.Breed
import com.jain.ullas.cats.usecases.GetBreedsUseCase

class BreedsViewModel(private val useCase: GetBreedsUseCase) : AbstractViewModel() {

    companion object {
        private val TAG = BreedsViewModel::class.java.simpleName
    }

    private val mutableLiveData = MutableLiveData<BreedsViewModelData>()
    val liveData: LiveData<BreedsViewModelData> = mutableLiveData

    internal fun loadBreeds() {
        mutableLiveData.postValue(BreedsViewModelData(true))
        launch {
            useCase.getBreeds()
                .subscribe({
                    mutableLiveData.postValue(BreedsViewModelData(false, it))
                }, {
                    mutableLiveData.postValue(BreedsViewModelData(false))
                    Log.d(TAG, it.message, it)
                })
        }
    }

}

data class BreedsViewModelData(val isLoading: Boolean = false, val breeds: List<Breed>? = null)