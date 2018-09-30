package io.hudepohl.mvvm.ui

import android.support.v4.app.Fragment
import kotlin.reflect.KClass


abstract class BaseFragment : Fragment() {

    @Suppress("UNCHECKED_CAST")
    fun <VM: BaseViewModel> getViewModel(viewModelClass: KClass<VM>): VM =
        (activity as? BaseActivity<BaseViewModel>)?.getViewModel(viewModelClass.java)
                ?: throw RuntimeException("Fragment's Activity must be of type ${BaseActivity::class.java.simpleName}")
}