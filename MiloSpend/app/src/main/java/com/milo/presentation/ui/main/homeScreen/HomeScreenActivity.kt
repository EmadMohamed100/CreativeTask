package com.milo.presentation.ui.main.homeScreen

import android.os.Bundle
import com.milo.app.R
import com.milo.presentation.ui.base.activity.BaseActivity
import com.milo.presentation.ui.main.login.LoginScreenFragment
import com.milo.presentation.ui.main.splash.SplashScreen
import com.milo.presentation.ui.utilities.showFragment

class HomeScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        showSplashScreen()
    }

    fun showSplashScreen() {
        showFragment(
            SplashScreen.newInstance(),
            R.id.home_frame_container, false
        )
    }

    fun showLoginScreen() {
        showFragment(
            LoginScreenFragment.newInstance(),
            R.id.home_frame_container, true
        )
    }

    override fun setupToolbar() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        handelOnBackPressed()
    }

    private fun handelOnBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(
            R.id.home_frame_container)
        if (currentFragment is LoginScreenFragment) {
            finish()
        } else
            super.onBackPressed()
    }

}
