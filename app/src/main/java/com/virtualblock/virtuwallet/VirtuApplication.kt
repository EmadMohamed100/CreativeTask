package com.virtualblock.virtuwallet

import android.app.Activity
import android.app.Application
import com.virtualblock.virtuwallet.di.components.DaggerApplicationComponent
import com.virtualblock.virtuwallet.di.modules.ContextModule
import com.virtualblock.virtuwallet.domain.network.Services
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject


/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class VirtuApplication : Application() {

    @Inject
    lateinit var services: Services
    @Inject
    lateinit var disposables: CompositeDisposable

    companion object {
        fun get(activity: Activity): VirtuApplication {
            return activity.application as VirtuApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupInjection()
        setupTimber()
    }

    private fun setupInjection() {
        DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()
            .inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}