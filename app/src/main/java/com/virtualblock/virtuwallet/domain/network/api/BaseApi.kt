package com.virtualblock.virtuwallet.domain.network.api

import com.virtualblock.virtuwallet.domain.network.Urls
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
interface BaseApi {

    @GET(Urls.ENDPOINTS.EXAMPLE)
    fun loginExample(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<Response<Any /* Response serialized model instead of Any */>>

}