package com.virtualblock.virtuwallet

import android.app.Activity
import android.app.Application
import com.virtualblock.virtuwallet.di.application.ApplicationComponent
import com.virtualblock.virtuwallet.di.application.DaggerApplicationComponent
import com.virtualblock.virtuwallet.di.application.modules.ApplicationModule
import timber.log.Timber

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class VirtuApplication : Application() {

  companion object {
    fun get(activity: Activity): VirtuApplication {
      return activity.application as VirtuApplication
    }
  }

  lateinit var component: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    setupInjection()
    setupTimber()
  }


  private fun setupInjection() {
    component = DaggerApplicationComponent.builder()
      .applicationModule(ApplicationModule(this))
      .build()
  }

  private fun setupTimber() {
    Timber.plant(Timber.DebugTree())
  }
}