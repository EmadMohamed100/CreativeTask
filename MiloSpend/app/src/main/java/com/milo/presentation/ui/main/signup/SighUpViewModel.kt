package com.milo.presentation.ui.main.signup

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milo.presentation.ui.utilities.Event


/**
 * Created by Emad Mohamed Oun on 2/4/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */
class SighUpViewModel : ViewModel() {

    private val _moveToVerification = MutableLiveData<Event<Unit>>()
    private val _moveToStartScreen = MutableLiveData<Event<Unit>>()
    private val _moveToLoginScreen = MutableLiveData<Event<Unit>>()

    private val termsAndConditionsLiveData = MutableLiveData<Event<Unit>>()

    val username = MutableLiveData<String>("")
    val code = MutableLiveData<String>("")
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val confirmPassword = MutableLiveData<String>("")
    val loader = MutableLiveData<Boolean>(false)
    var spinnerVisibility = MutableLiveData<Boolean>(false)
    val country = MutableLiveData<Int>(0)
    var country_name = ""

    val moveToVerification: LiveData<Event<Unit>>
        get() = _moveToVerification

    val moveToStartScreen: LiveData<Event<Unit>>
        get() = _moveToStartScreen

    val moveToLoginScreen: LiveData<Event<Unit>>
        get() = _moveToLoginScreen


    val openTermsLiveData: LiveData<Event<Unit>>
        get() = termsAndConditionsLiveData

    fun onBackClicked() {
        _moveToStartScreen.value = Event(Unit)
    }


    fun openTermsAndConditionDialog() {
        termsAndConditionsLiveData.value = Event(Unit)
    }

    fun onLoginClicked() {
        _moveToLoginScreen.value = Event(Unit)
    }

    fun onSignupClicked() {
        _moveToVerification.value = Event(Unit)

    }

    fun isValidForm(
        email: String,
        username: String,
        password: String,
        confirmPassword: String,
        countryName: String
//        countries: List<Country>
    ): Boolean {
        if (TextUtils.isEmpty(email)) {
            return false
        }
        if (TextUtils.isEmpty(username)) {
            return false
        }
        if (TextUtils.isEmpty(password)) {
            return false
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            return false
        }
        if (password != confirmPassword)
            return false
//        if (TextUtils.isEmpty(countryName)) {
//            spinnerVisibility.value = true
//            if (countries.isNotEmpty()) {
//                if (country.value == 0) {
//                    return false
//                } else {
//                    country_name = countries[country.value!!-1].name
//                    return true
//                }
//            }
//        } else {
//            country_name = countryName
//            return true
//        }
        return true
    }
}