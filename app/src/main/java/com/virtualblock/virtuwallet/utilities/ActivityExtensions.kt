package com.virtualblock.virtuwallet.utilities

import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virtualblock.virtuwallet.presentation.ui.base.activity.BaseActivity

/**
 * Authored by Mohamed Fathy on 25 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun BaseActivity.hideKeypad() {
  currentFocus?.let {
    val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(it.windowToken, 0)
  }
}

fun BaseActivity.showFragment(
  fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
  fragment?.let {
    supportFragmentManager.apply {
      beginTransaction().apply {
        setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
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

fun BaseActivity.clearBackStack() {
  supportFragmentManager?.let {
    it.apply {
      if (backStackEntryCount > 0) {
        popBackStack(
          getBackStackEntryAt(0).id,
          androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
      }
    }
  }
}

fun <T : ViewModel> BaseActivity.getViewModel(
  modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
  return viewModelFactory?.let {
    androidx.lifecycle.ViewModelProviders.of(this, it).get(modelClass)
  } ?: androidx.lifecycle.ViewModelProviders.of(this).get(modelClass)
}