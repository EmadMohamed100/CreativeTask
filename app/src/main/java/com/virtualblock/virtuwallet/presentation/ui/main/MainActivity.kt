package com.virtualblock.virtuwallet.presentation.ui.main

import androidx.lifecycle.Observer
import com.virtualblock.virtuwallet.R
import com.virtualblock.virtuwallet.databinding.MainActivityBinding
import com.virtualblock.virtuwallet.presentation.ui.base.activity.BindableActivity
import com.virtualblock.virtuwallet.utilities.getViewModel
import kotlinx.android.synthetic.main.toolbar.toolbar

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class MainActivity : BindableActivity<MainActivityBinding>() {

  private val viewModel: MainViewModel by lazy {
    getViewModel(MainViewModel::class.java)
  }

  override fun getLayoutId(): Int = R.layout.main_activity

  override fun setupViewModel(viewBinding: MainActivityBinding) {
    viewBinding.apply {
      setLifecycleOwner(this@MainActivity)
      viewModel = this@MainActivity.viewModel
    }
  }

  override fun observeData() {
    viewModel.buttonClick.observe(this, Observer { viewModel.text.value = "Hello, World!" })
  }

  override fun init() {
  }

  override fun setupToolbar() {
    setSupportActionBar(toolbar)
  }
}