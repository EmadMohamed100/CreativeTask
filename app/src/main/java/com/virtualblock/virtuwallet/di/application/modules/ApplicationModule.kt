package com.virtualblock.virtuwallet.di.application.modules

import android.app.Application
import android.content.Context
import com.virtualblock.virtuwallet.di.application.qualifiers.ApplicationContext
import com.virtualblock.virtuwallet.di.application.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Authored by Mohamed Fathy on 06 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@Module
class ApplicationModule(private val application: Application) {

  @Provides
  @ApplicationScope
  @ApplicationContext fun getContext(): Context = application.applicationContext
}
