package com.virtualblock.virtuwallet.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.virtualblock.virtuwallet.R
import com.virtualblock.virtuwallet.VirtuApplication
import com.virtualblock.virtuwallet.domain.network.Services
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class ExampleActivity : AppCompatActivity(), Services.OnResponseReceived {

    private lateinit var services: Services
    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        val application = VirtuApplication.get(this)
        services = application.services
        disposables = application.disposables

        services.setResponseListener(this)
        services.example("Name", "Pass")
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    override fun onResponse(model: Any /* The serialized model we have passed in BaseApi function */) {

    }
}