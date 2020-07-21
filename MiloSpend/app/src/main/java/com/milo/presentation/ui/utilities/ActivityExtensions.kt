package com.milo.presentation.ui.utilities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils
import com.milo.presentation.ui.base.activity.BaseActivity


/**
 * Authored by Mohamed Fathy on 25 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
fun BaseActivity.startActivity(cls: Class<*>, extras: Bundle? = null) {
    val intent = Intent(this, cls)
    extras?.let { intent.putExtras(it) }
    startActivity(intent)
}

fun BaseActivity.hideKeypad() {
    currentFocus?.let {
        val imm =
            getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}

fun BaseActivity.showFragment(
    fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
    hideKeypad()
    fragment?.let {
        supportFragmentManager.apply {
            beginTransaction().apply {
                setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                if (replace) {
                    replace(container, fragment).commit()
                } else {
                    addToBackStack(null)
                    add(container, fragment).commit()
                    executePendingTransactions()
                }

            }
        }
        return it
    }
    return null
}


fun BaseActivity.showFragment2(
    fragment: Fragment?, @IdRes container: Int, replace: Boolean
): Fragment? {
    fragment?.let {
        supportFragmentManager.apply {
            beginTransaction().apply {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                if (replace) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                        replace(container, fragment).commitNow()
                    else
                        replace(container, fragment).commit()
                } else {
                    replace(container, fragment).commit()
                    addToBackStack(null)
                    executePendingTransactions()
                }
            }
        }
        return it
    }
    return null
}

fun BaseActivity.clearBackStack() {
    supportFragmentManager.let {
        it.apply {
            for (i in 0 until it.backStackEntryCount) {
                it.popBackStack()
            }
        }
    }
}

fun <T : ViewModel> BaseActivity.getViewModel(
    modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
    return viewModelFactory?.let {
        ViewModelProviders.of(this, it).get(modelClass)
    } ?: ViewModelProviders.of(this).get(modelClass)
}

fun BaseActivity.getSupportColor(
    @ColorRes idRes: Int
): Int? = ContextCompat.getColor(this, idRes)

fun BaseActivity.getDrawable(
    @DrawableRes idRes: Int
): Drawable? = ContextCompat.getDrawable(this, idRes)

fun BaseActivity.showToast(message: String) {
    SuperActivityToast.create(this, Style(), Style.TYPE_BUTTON)
        .setProgressBarColor(Color.WHITE)
        .setText(message)
        .setDuration(Style.DURATION_SHORT)
        .setFrame(Style.FRAME_LOLLIPOP)
        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
        .setAnimations(Style.ANIMATIONS_POP).show()
}

fun Activity.showToast(message: String) {
    SuperActivityToast.create(this, Style(), Style.TYPE_BUTTON)
        .setProgressBarColor(Color.WHITE)
        .setText(message)
        .setDuration(Style.DURATION_SHORT)
        .setFrame(Style.FRAME_LOLLIPOP)
        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
        .setAnimations(Style.ANIMATIONS_POP).show()
}