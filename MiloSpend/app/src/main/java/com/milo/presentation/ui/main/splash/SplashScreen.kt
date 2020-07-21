package com.milo.presentation.ui.main.splash

import android.os.CountDownTimer
import com.milo.app.R
import com.milo.presentation.ui.base.fragment.BaseFragment
import com.milo.presentation.ui.main.homeScreen.HomeScreenActivity


/**
 * Created by Emad Mohamed Oun on 2/4/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class SplashScreen : BaseFragment() {

    companion object {
        fun newInstance(): SplashScreen {
            return SplashScreen()
        }
    }

    override fun getLayoutId(): Int = R.layout.splash_fragment

    override fun init() {
        delayedViewingTheSplashContainer()
    }

    private fun delayedViewingTheSplashContainer() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                (activity as HomeScreenActivity).showLoginScreen()
            }

            override fun onTick(p0: Long) {}
        }.start()
    }
}