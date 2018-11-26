package com.virtualblock.virtuwallet.utilities

import android.content.Context
import android.net.ConnectivityManager

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
object Utils {

    fun isNetworkAvailable(context: Context?): Boolean {
        context?.let {
            val connectivity = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivity.apply {
                val isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
                if (!isConnected) context.toast("Network is not available")
                return isConnected
            }
        }
    }
}
