package com.milo.presentation.ui.utilities

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milo.presentation.ui.base.activity.BaseActivity
import com.milo.presentation.ui.base.fragment.BaseFragment


/**
 * Authored by Mohamed Fathy on 25 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun BaseFragment.startActivity(cls: Class<*>, extras: Bundle? = null) {
    activity?.let {
        val intent = Intent(it, cls)
        extras?.let { bundle -> intent.putExtras(bundle) }
        startActivity(intent)
    }
}

fun BaseFragment.toast(msg: String?) {
    msg?.let {
    }
}

fun BaseFragment.toastLong(msg: String?) {
    msg?.let {
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

fun BaseFragment.showFragmentOnActivity(
    fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
    return (activity as BaseActivity).showFragment(fragment, container, replace)
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
    return null
}



fun BaseFragment.close() {
    hideKeypad()
    fragmentManager?.popBackStack()
}

fun <T : ViewModel> BaseFragment.getViewModel(
    modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        androidx.lifecycle.ViewModelProviders.of(this, it).get(modelClass)
    } ?: androidx.lifecycle.ViewModelProviders.of(this).get(modelClass)
}

fun BaseFragment.getVectorBitmap(@DrawableRes resId: Int): Bitmap? {
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
    return null
}

fun BaseFragment.getColor(
    @ColorRes idRes: Int
) = activity?.let { ContextCompat.getColor(it, idRes) }

fun BaseFragment.getDrawable(
    @DrawableRes idRes: Int
) = activity?.let { ContextCompat.getDrawable(it, idRes) }


fun Context.openBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

fun String.toDouble(): Double = java.lang.Double.parseDouble(this)

fun Context.makeTextBold(inputText: String): String = "<b>$inputText</b>"


fun updateAccentColor(actionBar: ActionBar) {
    val colorDrawable = ColorDrawable(Color.parseColor("#000000"))
    actionBar.setBackgroundDrawable(colorDrawable)

}
