package com.virtualblock.virtuwallet.di.presentation.modules

import android.content.Context
import com.virtualblock.virtuwallet.di.presentation.qualifiers.ActivityContext
import com.virtualblock.virtuwallet.di.presentation.scopes.ActivityScope
import com.virtualblock.virtuwallet.presentation.ui.base.activity.BaseActivity
import dagger.Module
import dagger.Provides

/**
 * Authored by Mohamed Fathy on 04 Dec, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@Module
class PresentationModule(private val activity: BaseActivity) {

  @Provides
  @ActivityScope
  @ActivityContext fun getContext(): Context = activity
}
