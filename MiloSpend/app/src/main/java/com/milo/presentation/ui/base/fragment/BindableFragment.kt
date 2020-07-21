package com.milo.presentation.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.milo.app.R

/**
 * Authored by Mohamed Fathy on 16 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BindableFragment<in T : ViewDataBinding> : BaseFragment() {

    private lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        val view = binding.root
        getColor(context!!, R.color.white).let { view.setBackgroundColor(it) }
        view.isClickable = true
        view.isFocusable = true
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel(binding)
        observeData()
    }

    protected abstract fun setupViewModel(viewBinding: T)

    protected abstract fun observeData()
}