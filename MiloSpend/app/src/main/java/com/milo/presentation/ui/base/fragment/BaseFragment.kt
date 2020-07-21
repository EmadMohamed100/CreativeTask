package com.milo.presentation.ui.base.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


/**
 * Authored by Mohamed Fathy on 17 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BaseFragment : Fragment() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let { receiveArgs(it) }
  }


  open fun receiveArgs(args: Bundle) {}


  @SuppressLint("ResourceAsColor")
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(getLayoutId(), container, false)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    init()
    setupToolbar()
  }

  @LayoutRes protected abstract fun getLayoutId(): Int

  protected abstract fun init()

  open fun setupToolbar() {}

}