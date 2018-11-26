package com.virtualblock.virtuwallet.di.components

import com.virtualblock.virtuwallet.VirtuApplication
import com.virtualblock.virtuwallet.di.modules.BaseApiModule
import com.virtualblock.virtuwallet.di.modules.ContextModule
import com.virtualblock.virtuwallet.di.modules.RxModule
import dagger.Component

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
@Component(modules = [ContextModule::class, BaseApiModule::class, RxModule::class])
interface ApplicationComponent {

    fun inject(application: VirtuApplication)
}
