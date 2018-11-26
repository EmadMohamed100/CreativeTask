package com.rytalo.rtaxi.utilities

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun Fragment.toast(msg: String?) {
    msg?.let {
        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toastLong(msg: String?) {
    msg?.let {
        Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
    }
}

fun Fragment.showFragment(
    fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
    fragment?.let {
        childFragmentManager.apply {
            beginTransaction().apply {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                if (replace) {
                    replace(container, fragment).commit()
                } else {
                    add(container, fragment).commit()
                    addToBackStack(null)
                    executePendingTransactions()
                }
            }
        }
        return it
    }
}

fun <T : ViewModel> Fragment.getViewModel(
    modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        ViewModelProviders.of(this, it).get(modelClass)
    } ?: ViewModelProviders.of(this).get(modelClass)
}
