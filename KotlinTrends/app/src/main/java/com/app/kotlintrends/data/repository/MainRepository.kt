package com.app.kotlintrends.data.repository

import com.app.kotlintrends.data.api.ApiService
import com.app.kotlintrends.data.model.RepositoriesListResponse
import com.app.kotlintrends.utils.Resource
import io.reactivex.Single
import retrofit2.http.Query


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class MainRepository (private val apiService : ApiService) {

    fun getGithubRepositories(pageNumber: Int, countPerPage: Int,
                              accessToken: String): Single<ArrayList<RepositoriesListResponse>> {
        return apiService.getRepositories(pageNumber, countPerPage, accessToken)
    }
}
