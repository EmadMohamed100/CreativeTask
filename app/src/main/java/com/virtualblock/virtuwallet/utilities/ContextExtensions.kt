package com.virtualblock.virtuwallet.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun Context.toast(msg: String?) {
    msg?.let {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
}

fun Context.toastLong(msg: String?) {
    msg?.let {
        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
    }
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivity.apply {
        val isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        if (!isConnected) toast("Network is not available")
        return isConnected
    }
}