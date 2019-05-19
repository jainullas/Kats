package com.jain.ullas.cats.presentation

import android.util.Log
import com.jain.ullas.cats.usecases.GetRandomCatUseCase
import io.reactivex.disposables.CompositeDisposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MainPresenter(private val view: MainView) : KoinComponent {
    private val TAG = MainPresenter::class.java.simpleName
    private val repository: GetRandomCatUseCase by inject()
    private val compositeDisposable = CompositeDisposable()


    internal fun loadRandomCat() {
        view.showProgress()
        compositeDisposable.add(
            repository.getRandomCat()
                .subscribe({ randomCat ->
                    view.hideProgress()
                    if (randomCat.url?.isNotBlank() == true) {
                        view.showRandomCat(randomCat.url)
                    }
                }, {
                    view.hideProgress()
                    Log.d(TAG, "", it)

                })
        )
    }

    internal fun onRefreshButtonClicked() {
        loadRandomCat()
    }

}