package com.milo.presentation.ui.main.login

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.milo.app.R
import com.milo.app.databinding.LoginFragmentBinding
import com.milo.presentation.ui.base.fragment.BindableFragment
import com.milo.presentation.ui.main.signup.SignUpFragment
import com.milo.presentation.ui.main.homeScreen.HomeScreenActivity
import com.milo.presentation.ui.utilities.getViewModel
import com.milo.presentation.ui.utilities.showFragment
import com.milo.presentation.ui.utilities.showToast


/**
 * Created by Emad Mohamed Oun on 2/4/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */
class LoginScreenFragment : BindableFragment<LoginFragmentBinding>() {

    private val phoneErrorTextView: TextView?
        get() = wrong_phone_edit_text

    private val passwordErrorTextView: TextView?
        get() = wrong_pw_edit_text

    companion object {
        fun newInstance() =
            LoginScreenFragment()
    }

    private val signInViewModel: LoginViewModel by lazy {
        getViewModel(
            LoginViewModel::class.java
        )
    }

    override fun setupViewModel(viewBinding: LoginFragmentBinding) {
        viewBinding.apply {
            lifecycleOwner = this@LoginScreenFragment
            signInViewModel = this@LoginScreenFragment.signInViewModel
        }
    }

    override fun observeData() {
        signInViewModel.moveToSignup.observe(this, Observer {
            showFragments(SignUpFragment.newInstance())
        })

        signInViewModel.moveToMain.observe(this, Observer {
            if (signInViewModel.isEmailAndPasswordValid(
                    signInViewModel.phoneCode.value.toString(),
                    signInViewModel.phoneNumber.value.toString(),
                    signInViewModel.password.value.toString())) {
                phoneErrorTextView!!.visibility = View.GONE
                passwordErrorTextView!!.visibility = View.GONE
                // Go to Main Screen
            } else {
                (activity as HomeScreenActivity).showToast(getString(R.string.fill_all_fields))
            }
        })
    }

    private fun showFragments(destination: Fragment) {
        (activity as HomeScreenActivity).showFragment(destination,
            R.id.home_frame_container, false)
    }

    override fun getLayoutId(): Int = R.layout.login_fragment

    override fun init() {

    }
}