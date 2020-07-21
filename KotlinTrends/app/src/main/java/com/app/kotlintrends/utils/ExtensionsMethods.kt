package com.app.kotlintrends.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.kotlintrends.ui.main.MainActivity


/**
 * Created by Emad Mohamed Oun on 7/20/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

fun <T : ViewModel> MainActivity.getViewModel(
    modelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        ViewModelProvider(this, it).get(modelClass)
    } ?: ViewModelProvider(this).get(modelClass)
}

fun MainActivity.openBrowser(url : String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}