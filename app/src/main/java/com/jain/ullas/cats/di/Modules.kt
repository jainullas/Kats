package com.jain.ullas.cats.di

import com.jain.ullas.cats.BuildConfig
import com.jain.ullas.cats.common.AsyncFlowableTransformer
import com.jain.ullas.cats.presentation.MainViewModel
import com.jain.ullas.cats.presentation.MainView
import com.jain.ullas.cats.repository.CatApi
import com.jain.ullas.cats.repository.CatRepository
import com.jain.ullas.cats.usecases.GetRandomCatUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single(name = INSTANCE_OF_OK_HTTP) {
        createOkHttpClient()
    }

    single(name = API) {
        createRetrofit<CatApi>(get(INSTANCE_OF_OK_HTTP))
    }

}


val repositoryModule = module {

    single(name = REPOSITORY) {
        CatRepository(api = get(API))
    }

}

val useCaseModules = module {

    factory(name = INSTANCE_OF_GET_NEWS_USE_CASE) {
        GetRandomCatUseCase(
            transformer = AsyncFlowableTransformer(),
            catRepository = get()
        )
    }
}

val applicationModule = module {
    viewModel { MainViewModel(get(INSTANCE_OF_GET_NEWS_USE_CASE)) }
}


fun createOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply
        {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()


inline fun <reified T> createRetrofit(okHttpClient: OkHttpClient): T =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(T::class.java)


private const val INSTANCE_OF_OK_HTTP = "instance_of_ok_http"
private const val API = "api"
private const val REPOSITORY = "repository"
private const val INSTANCE_OF_GET_NEWS_USE_CASE = "instance_of_get_news_use_case"