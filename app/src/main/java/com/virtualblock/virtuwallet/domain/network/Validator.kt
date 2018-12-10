package com.virtualblock.virtuwallet.domain.network

import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Authored by Mohamed Fathy on 26 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
class Validator @Inject constructor() {

  private var responseReceived: Services.OnResponseReceived? = null

  fun setResponseListener(responseReceived: Services.OnResponseReceived? = null) {
    this.responseReceived = responseReceived
  }

  /**
   * Validate response body to check if it's error or not
   *
   * @param response body
   */
  fun validateResponse(response: Response<*>?) {
    response?.let {
      it.apply {
        if (isSuccessful) {
          responseReceived?.let { responseInterface ->
            body()?.let { model ->
              responseInterface.onResponse(model)
            } ?: Timber.e("Model = null")
          }
        } else {
          Timber.e("Response code is %i", response.code())
        }
      }
    } ?: Timber.e("Response = null")
  }
}
