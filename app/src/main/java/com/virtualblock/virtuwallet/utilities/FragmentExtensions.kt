package com.virtualblock.virtuwallet.utilities

import android.content.Context
import android.graphics.Bitmap
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virtualblock.virtuwallet.presentation.ui.base.fragment.BaseFragment


/**
 * Authored by Mohamed Fathy on 25 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun BaseFragment.toast(msg: String?) {
  msg?.let {
    android.widget.Toast.makeText(activity, it, android.widget.Toast.LENGTH_SHORT).show()
  }
}

fun BaseFragment.toastLong(msg: String?) {
  msg?.let {
    android.widget.Toast.makeText(activity, it, android.widget.Toast.LENGTH_LONG).show()
  }
}

fun BaseFragment.hideKeypad() {
  activity?.let { activity ->
    activity.currentFocus?.let { view ->
      val imm =
        activity.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }
}

fun BaseFragment.showKeypad() {
  activity?.let { activity ->
    val manager =
      activity.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.toggleSoftInput(
      android.view.inputmethod.InputMethodManager.SHOW_FORCED,
      android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
    )
  }
}

fun BaseFragment.showFragment(
  fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
  fragment?.let {
    childFragmentManager.apply {
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

fun <T : ViewModel> BaseFragment.getViewModel(
  modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
  return viewModelFactory?.let {
    androidx.lifecycle.ViewModelProviders.of(this, it).get(modelClass)
  } ?: androidx.lifecycle.ViewModelProviders.of(this).get(modelClass)
}

fun BaseFragment.getVectorBitmap(@DrawableRes resId: Int): Bitmap {
  androidx.core.content.ContextCompat.getDrawable(activity as Context, resId)?.let {
    it.apply {
      androidx.core.graphics.drawable.DrawableCompat.wrap(this).mutate()
      val bitmap = android.graphics.Bitmap.createBitmap(
        intrinsicWidth,
        intrinsicHeight,
        android.graphics.Bitmap.Config.ARGB_8888
      )
      val canvas = android.graphics.Canvas(bitmap)
      setBounds(0, 0, canvas.width, canvas.height)
      draw(canvas)
      return bitmap
    }
  }
}
