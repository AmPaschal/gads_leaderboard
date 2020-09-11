package com.ampaschal.gadsleaderboard.koin

import com.ampaschal.gadsleaderboard.network.LearnersApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory { provideOkHttpClient() }
    factory { provideApiService(get()) }
    single { provideRetrofit(get()) }
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://gadsapi.herokuapp.com")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): LearnersApi = retrofit.create(LearnersApi::class.java)