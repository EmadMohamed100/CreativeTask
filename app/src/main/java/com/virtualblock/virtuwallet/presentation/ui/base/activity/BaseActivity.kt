package com.virtualblock.virtuwallet.presentation.ui.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.virtualblock.virtuwallet.VirtuApplication
import com.virtualblock.virtuwallet.di.presentation.ActivityComponent
import com.virtualblock.virtuwallet.di.presentation.modules.ActivityModule
import com.virtualblock.virtuwallet.di.presentation.modules.PresentationModule

/**
 * Authored by Mohamed Fathy on 06 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BaseActivity : AppCompatActivity() {

  private val activityComponent: ActivityComponent
    get() {
      return VirtuApplication.get(this).component
        .newActivityComponent(PresentationModule(this), ActivityModule(this))
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    intent?.let { receiveArgs(it) }
    injectThis(activityComponent)
    setContentView(getLayoutId())
    init()
    setupToolbar()
  }

  open fun receiveArgs(intent: Intent) {}

  open fun injectThis(component: ActivityComponent) {}

  @LayoutRes protected abstract fun getLayoutId(): Int

  protected abstract fun init()

  protected abstract fun setupToolbar()
}