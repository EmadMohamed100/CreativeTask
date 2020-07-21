package com.app.kotlintrends.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.kotlintrends.data.model.RepositoriesListResponse
import com.app.kotlintrends.data.repository.MainRepository
import com.app.kotlintrends.utils.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var compositeDisposable: CompositeDisposable? = null

    private val _repositories = MutableLiveData<Resource<ArrayList<RepositoriesListResponse>>>()
    private val _repositoriesNextPage =
        MutableLiveData<Resource<ArrayList<RepositoriesListResponse>>>()

    val repositoriesNextPage: LiveData<Resource<ArrayList<RepositoriesListResponse>>>
        get() = _repositoriesNextPage
    val repositories: LiveData<Resource<ArrayList<RepositoriesListResponse>>>
        get() = _repositories

    fun getGithubRepositoriesData(
        pageNumber: Int,
        accessToken: String
    ) {
        _repositories.postValue(Resource.loading(null))
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(
            mainRepository.getGithubRepositories(pageNumber, 10, accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (pageNumber == 1) {
                        _repositories.value = Resource.success(it)
                    } else {
                        _repositoriesNextPage.value = Resource.success(it)
                    }
                }, {
                    _repositories.value = Resource.error("Something Went Wrong", null)
                })
        )
    }

    @SuppressLint("CheckResult")
    fun callApiEveryHour(pageNumber: Int,
                         accessToken: String) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(
            Observable.interval(1L, TimeUnit.HOURS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { time ->
                    run {
                        getGithubRepositoriesData(pageNumber, accessToken)
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        resetDisposable()
    }

        private fun resetDisposable() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}