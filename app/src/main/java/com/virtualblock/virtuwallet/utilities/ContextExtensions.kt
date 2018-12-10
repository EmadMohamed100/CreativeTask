package com.virtualblock.virtuwallet.utilities

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.virtualblock.virtuwallet.R

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

fun Context.getAppColor(
  @ColorRes idRes: Int
): Int? = ContextCompat.getColor(this, idRes)

fun Context.grapAppDrawable(
  @DrawableRes idRes: Int
): Drawable? = ContextCompat.getDrawable(this, idRes)

fun Context.getAccentColor(): Int {
  val typedValue = TypedValue()
  val array = obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
  val color = array.getColor(0, 0)
  array.recycle()
  return color
}