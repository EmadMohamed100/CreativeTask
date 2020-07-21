package com.milo.presentation.ui.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Authored by Mohamed Fathy on 16 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BindableActivity<in T : ViewDataBinding> : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupViewModel(DataBindingUtil.setContentView(this, getLayoutId()))
    observeData()
  }

  protected abstract fun setupViewModel(viewBinding: T)

  protected abstract fun observeData()

  override fun onResume() {
    super.onResume()
  }
}