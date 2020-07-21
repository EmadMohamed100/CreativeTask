package com.milo.presentation.ui.main.signup

import androidx.lifecycle.Observer
import com.milo.app.R
import com.milo.app.databinding.SignUpFragmentBinding
import com.milo.presentation.ui.base.fragment.BindableFragment
import com.milo.presentation.ui.utilities.close
import com.milo.presentation.ui.utilities.getViewModel
import com.milo.presentation.ui.utilities.showTermsAndConditionsDialog


/**
 * Created by Emad Mohamed Oun on 2/4/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */
class SignUpFragment : BindableFragment<SignUpFragmentBinding>() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private val signUpModel: SighUpViewModel by lazy {
        getViewModel(
            SighUpViewModel::class.java
        )
    }

    override fun setupViewModel(viewBinding: SignUpFragmentBinding) {
        viewBinding.apply {
            setLifecycleOwner(this@SignUpFragment)
            signupViewmodel = this@SignUpFragment.signUpModel
        }
    }


    override fun observeData() {
        signUpModel.moveToStartScreen.observe(this@SignUpFragment, Observer {
            close()
        })

        signUpModel.openTermsLiveData.observe(this, Observer {
            showTermsAndConditionsDialog()
        })

        signUpModel.moveToLoginScreen.observe(this, Observer {
            close()
        })
    }

    override fun getLayoutId(): Int = R.layout.sign_up_fragment

    override fun init() {
    }


}