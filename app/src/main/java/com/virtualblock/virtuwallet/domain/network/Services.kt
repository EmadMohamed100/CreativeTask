package com.virtualblock.virtuwallet.domain.network

import android.content.Context
import com.google.gson.Gson
import com.virtualblock.virtuwallet.domain.network.api.BaseApi
import com.virtualblock.virtuwallet.utilities.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class Services @Inject constructor(
    private val context: Context, private val api: BaseApi,
    private val disposables: CompositeDisposable,
    private val validator: Validator
) {

    fun example(username: String, password: String) {
        if (isConnected) {
            val disposable = api.loginExample(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::validateResponse, this::onFail)
            disposables.add(disposable)
        }
    }

    private val isConnected: Boolean
        get() = Utils.isNetworkAvailable(context)

    fun setResponseListener(responseReceived: OnResponseReceived? = null) {
        validator.setResponseListener(responseReceived)
    }

    private fun validateResponse(response: Response<*>?) {
        validator.validateResponse(response)
    }

    private fun onFail(t: Throwable) {
        validateResponse(null)
        Timber.e(t)
    }

    /**
     * Interface to pass service response when arrived.
     */
    interface OnResponseReceived {

        /**
         * Pass model filled with data from the service
         *
         * @param model serialized [Gson] model filled with data
         */
        fun onResponse(model: Any)
    }
}
