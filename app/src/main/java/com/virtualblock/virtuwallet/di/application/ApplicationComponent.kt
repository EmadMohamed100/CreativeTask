package com.virtualblock.virtuwallet.di.application

import com.virtualblock.virtuwallet.di.application.modules.ApplicationModule
import com.virtualblock.virtuwallet.di.application.modules.BaseApiModule
import com.virtualblock.virtuwallet.di.application.scopes.ApplicationScope
import com.virtualblock.virtuwallet.di.presentation.ActivityComponent
import com.virtualblock.virtuwallet.di.presentation.FragmentComponent
import com.virtualblock.virtuwallet.di.presentation.modules.ActivityModule
import com.virtualblock.virtuwallet.di.presentation.modules.FragmentModule
import com.virtualblock.virtuwallet.di.presentation.modules.PresentationModule
import dagger.Component

/**
 * Authored by Mohamed Fathy on 06 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@ApplicationScope
@Component(modules = [BaseApiModule::class, ApplicationModule::class])
interface ApplicationComponent {

  fun newFragmentComponent(
    presentationModule: PresentationModule,
    fragmentModule: FragmentModule
  ): FragmentComponent

  fun newActivityComponent(
    presentationModule: PresentationModule,
    activityModule: ActivityModule
  ): ActivityComponent
}
