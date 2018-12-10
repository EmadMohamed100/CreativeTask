package com.virtualblock.virtuwallet.di.application.modules

import com.virtualblock.virtuwallet.BuildConfig
import com.virtualblock.virtuwallet.di.application.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

/**
 * Authored by Mohamed Fathy on 06 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@Module
class NetworkModule {

  @Provides
  @ApplicationScope fun getOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(20, TimeUnit.SECONDS)
      .writeTimeout(20, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .retryOnConnectionFailure(true)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @ApplicationScope fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    return logging
  }
}
