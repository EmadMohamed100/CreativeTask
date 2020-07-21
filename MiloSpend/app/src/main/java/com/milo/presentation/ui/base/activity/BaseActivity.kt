package com.milo.presentation.ui.base.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.milo.MiloApp


/**
 * Authored by Mohamed Fathy on 06 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var crowdFunding: MiloApp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crowdFunding = this.applicationContext as MiloApp
        intent?.let { receiveArgs(it) }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        setContentView(getLayoutId())
        init()
        setupToolbar()
    }

    override fun onResume() {
        super.onResume()
    }

    open fun receiveArgs(intent: Intent) {}


    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun init()

    protected abstract fun setupToolbar()


}