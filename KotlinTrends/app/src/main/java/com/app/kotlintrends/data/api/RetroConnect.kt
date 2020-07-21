package com.app.kotlintrends.data.api

import com.app.kotlintrends.BuildConfig
import com.app.kotlintrends.utils.NullOnEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

object RetroConnect {

    fun getCampaignsApi(): ApiService =
        getRetrofit().create(ApiService::class.java)

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .client(getOkHttpClient(getHttpLoggingInterceptor()))
        .build()

    private fun getGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    private fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }
}