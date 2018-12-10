package com.virtualblock.virtuwallet.di.application.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.virtualblock.virtuwallet.BuildConfig
import com.virtualblock.virtuwallet.di.application.scopes.ApplicationScope
import com.virtualblock.virtuwallet.domain.network.api.BaseApi
import com.virtualblock.virtuwallet.domain.network.converters.NullOnEmptyConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Authored by Mohamed Fathy on 06 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@Module(includes = [NetworkModule::class])
class BaseApiModule {

  @Provides
  @ApplicationScope fun getBaseApi(
    retrofit: Retrofit
  ): BaseApi = retrofit.create(BaseApi::class.java)

  @Provides
  @ApplicationScope fun getRetrofit(
    client: OkHttpClient,
    factory: NullOnEmptyConverterFactory,
    gson: Gson
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(factory)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

  @Provides
  @ApplicationScope fun getGson(): Gson {
    return GsonBuilder().setLenient().create()
  }
}
