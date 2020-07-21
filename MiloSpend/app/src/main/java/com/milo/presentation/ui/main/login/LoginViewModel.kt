package com.milo.presentation.ui.main.login

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

class LoginViewModel constructor() : ViewModel() {

    private val _moveToMain = MutableLiveData<Event<Unit>>()
    private val _moveToStart = MutableLiveData<Event<Unit>>()
    private val _moveToSignup = MutableLiveData<Event<Unit>>()
    private val _moveToForget = MutableLiveData<Event<Unit>>()


    val loader = MutableLiveData<Boolean>().default(false)
    val phoneCode = MutableLiveData<String>().default("")
    val phoneNumber = MutableLiveData<String>().default("")
    val password = MutableLiveData<String>().default("")


    val moveToMain: LiveData<Event<Unit>>
        get() = _moveToMain

    val moveToStart: LiveData<Event<Unit>>
        get() = _moveToStart

    val moveToSignup: LiveData<Event<Unit>>
        get() = _moveToSignup

    val moveToForget: LiveData<Event<Unit>>
        get() = _moveToForget

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

    fun onSignInClicked() {
        _moveToMain.value = Event(Unit)

    }

    fun onBackBtnClicked() {
        _moveToStart.value = Event(Unit)
    }

    fun onSignupClicked() {
        _moveToSignup.value = Event(Unit)
    }

    fun onForgetPassClicked() {
        _moveToForget.value = Event(Unit)
    }

    fun isEmailAndPasswordValid(code: String, phone: String, pass: String): Boolean {
        // validate email and password
        if (TextUtils.isEmpty(code)) {
            return false
        }
        if (TextUtils.isEmpty(phone)) {
            return false
        }
        if (TextUtils.isEmpty(pass)) {
            return false
        }
        return true
    }
}

