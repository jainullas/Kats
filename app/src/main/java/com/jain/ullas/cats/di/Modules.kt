package com.jain.ullas.cats.di

import com.jain.ullas.cats.BuildConfig
import com.jain.ullas.cats.common.AsyncFlowableTransformer
import com.jain.ullas.cats.presentation.breeds.BreedsViewModel
import com.jain.ullas.cats.presentation.RandomCatViewModel
import com.jain.ullas.cats.repository.CatApi
import com.jain.ullas.cats.repository.CatRepository
import com.jain.ullas.cats.usecases.GetBreedsUseCase
import com.jain.ullas.cats.usecases.GetRandomCatUseCase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
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

    factory(name = INSTANCE_OF_GET_RANDOM_CAT_CASE) {
        GetRandomCatUseCase(
            transformer = AsyncFlowableTransformer(),
            catRepository = get()
        )
    }

    factory(name = INSTANCE_OF_GET_BREEDS_CAT_CASE) {
        GetBreedsUseCase(
            transformer = AsyncFlowableTransformer(),
            catRepository = get()
        )
    }
}

val applicationModule = module {
    viewModel { RandomCatViewModel(get(INSTANCE_OF_GET_RANDOM_CAT_CASE)) }
    viewModel { BreedsViewModel(get(INSTANCE_OF_GET_BREEDS_CAT_CASE)) }
}


fun createOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor())
        .addInterceptor(loggingInterceptor())
        .build()


private fun headerInterceptor(): (Interceptor.Chain) -> Response {
    return { chain ->
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("x-api-key", BuildConfig.API_KEY)
                    .build()
            )
        }
    }
}

private fun loggingInterceptor() =
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }



inline fun <reified T> createRetrofit(okHttpClient: OkHttpClient): T =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(T::class.java)


private const val INSTANCE_OF_OK_HTTP = "instance_of_ok_http"
private const val API = "api"
private const val REPOSITORY = "repository"
private const val INSTANCE_OF_GET_RANDOM_CAT_CASE = "instance_of_get_random_cat_case"
private const val INSTANCE_OF_GET_BREEDS_CAT_CASE = "instance_of_get_breeds_cat_case"