package com.app.creativetask.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.creativetask.ui.main.homescreen.HomeActivity


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

fun <T : ViewModel> HomeActivity.getViewModel(
    modelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        ViewModelProvider(this, it).get(modelClass)
    } ?: ViewModelProvider(this).get(modelClass)
}