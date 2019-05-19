package com.jain.ullas.cats

import android.app.Application
import com.jain.ullas.cats.di.applicationModule
import com.jain.ullas.cats.di.networkModule
import com.jain.ullas.cats.di.repositoryModule
import com.jain.ullas.cats.di.useCaseModules
import org.koin.android.ext.android.startKoin

class CatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            androidContext = this@CatApplication,
            modules = listOf(
                networkModule,
                repositoryModule,
                useCaseModules,
                applicationModule
            )
        )
    }
}