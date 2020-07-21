package com.milo

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity


class MiloApp : Application() {

    private var mCurrentActivity: AppCompatActivity? = null

    init {
        instance = this
    }

    companion object {
        fun getInstance(context: Context): MiloApp {
            return context as MiloApp
        }

        private var instance: MiloApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}