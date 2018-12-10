package com.virtualblock.virtuwallet.presentation.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.virtualblock.virtuwallet.R
import com.virtualblock.virtuwallet.VirtuApplication
import com.virtualblock.virtuwallet.di.presentation.FragmentComponent
import com.virtualblock.virtuwallet.di.presentation.modules.FragmentModule
import com.virtualblock.virtuwallet.di.presentation.modules.PresentationModule
import com.virtualblock.virtuwallet.presentation.ui.base.activity.BaseActivity
import com.virtualblock.virtuwallet.utilities.getAppColor


/**
 * Authored by Mohamed Fathy on 17 Nov, 2018.
 * Contact: m.fathy@rytalo.com
 * by Rytalo.
 */
abstract class BaseFragment : Fragment() {

  private val fragmentComponent: FragmentComponent
    get() {
      return VirtuApplication.get(activity as BaseActivity).component
        .newFragmentComponent(PresentationModule(activity as BaseActivity), FragmentModule(this))
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let { receiveArgs(it) }
    injectThis(fragmentComponent)
  }

  open fun receiveArgs(args: Bundle) {}

  open fun injectThis(component: FragmentComponent) {}

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(getLayoutId(), container, false)
    view.context.getAppColor(R.color.md_grey_100)?.let { view.setBackgroundColor(it) }
    view.isClickable = true
    view.isFocusable = true
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    init()
  }

  @LayoutRes protected abstract fun getLayoutId(): Int

  protected abstract fun init()
}