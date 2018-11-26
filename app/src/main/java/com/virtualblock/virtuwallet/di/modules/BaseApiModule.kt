package com.virtualblock.virtuwallet.di.modules

import com.google.gson.Gson
import com.virtualblock.virtuwallet.domain.network.Urls
import com.virtualblock.virtuwallet.domain.network.api.BaseApi
import com.virtualblock.virtuwallet.domain.network.converters.NullOnEmptyConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@Module(includes = [NetworkModule::class])
class BaseApiModule {

    @Provides
    fun provideBaseApi(retrofit: Retrofit): BaseApi {
        return retrofit.create(BaseApi::class.java)
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        factory: NullOnEmptyConverterFactory, gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(factory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideNullOnEmptyConverterFactory(): NullOnEmptyConverterFactory {
        return NullOnEmptyConverterFactory()
    }
}
