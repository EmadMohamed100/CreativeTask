package com.virtualblock.virtuwallet.utilities

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun AppCompatActivity.showFragment(
    fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
    fragment?.let {
        supportFragmentManager.apply {
            supportFragmentManager.beginTransaction().apply {
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

fun AppCompatActivity.clearBackStack() {
    supportFragmentManager?.let {
        it.apply {
            if (backStackEntryCount > 0) {
                popBackStack(getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }
}

fun <T : ViewModel> AppCompatActivity.getViewModel(
    modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        ViewModelProviders.of(this, it).get(modelClass)
    } ?: ViewModelProviders.of(this).get(modelClass)
}