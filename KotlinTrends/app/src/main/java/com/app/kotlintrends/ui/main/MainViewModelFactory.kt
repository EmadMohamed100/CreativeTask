@file:Suppress("UNCHECKED_CAST")

package com.app.kotlintrends.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.kotlintrends.data.api.ApiService
import com.app.kotlintrends.data.repository.MainRepository


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class MainViewModelFactory(private val apiHelper: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name need ${
        MainViewModel::class.java.simpleName} instance")
    }

}