package com.virtualblock.virtuwallet.di.presentation

import com.virtualblock.virtuwallet.di.presentation.modules.FragmentModule
import com.virtualblock.virtuwallet.di.presentation.modules.PresentationModule
import com.virtualblock.virtuwallet.di.presentation.scopes.ActivityScope
import dagger.Subcomponent

/**
 * Authored by Mohamed Fathy on 04 Dec, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@ActivityScope
@Subcomponent(modules = [PresentationModule::class, FragmentModule::class])
interface FragmentComponent {

}