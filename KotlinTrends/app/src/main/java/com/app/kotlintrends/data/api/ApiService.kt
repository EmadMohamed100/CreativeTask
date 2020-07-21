package com.app.kotlintrends.data.api

import com.app.kotlintrends.data.api.EndPoints.REPOSITORIES
import com.app.kotlintrends.data.model.RepositoriesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

interface ApiService {

    @GET(REPOSITORIES)
    fun getRepositories(@Query("page") pageNumber: Int,
                        @Query("per_page") countPerPage: Int,
                        @Query("access_token") accessToken: String):
                Single<ArrayList<RepositoriesListResponse>>
}