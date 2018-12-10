package com.virtualblock.virtuwallet.presentation.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.virtualblock.virtuwallet.R
import com.virtualblock.virtuwallet.utilities.getAppColor

/**
 * Authored by Mohamed Fathy on 16 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BindableFragment<in T : ViewDataBinding> : BaseFragment() {

  private lateinit var binding: T

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
    val view = binding.root
    view.context.getAppColor(R.color.md_grey_100)?.let { view.setBackgroundColor(it) }
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupViewModel(binding)
    observeData()
  }

  protected abstract fun setupViewModel(viewBinding: T)

  protected abstract fun observeData()
}