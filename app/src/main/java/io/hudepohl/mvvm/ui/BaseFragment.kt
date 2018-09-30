package io.hudepohl.mvvm.ui

import android.arch.lifecycle.ViewModel
import android.support.v4.app.Fragment
import kotlin.reflect.KClass


abstract class BaseFragment : Fragment() {

    fun <VM: ViewModel> getViewModel(viewModelClass: KClass<VM>): VM =
            (activity as BaseActivity).getViewModel(viewModelClass)
}