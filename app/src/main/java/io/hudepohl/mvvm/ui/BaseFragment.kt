package io.hudepohl.mvvm.ui

import android.support.v4.app.Fragment
import kotlin.reflect.KClass

abstract class BaseFragment : Fragment() {

    protected enum class ViewModelScope { ACTIVITY, FRAGMENT }

    @Suppress("UNCHECKED_CAST")
    protected fun <VM: BaseViewModel> getViewModel(
        viewModelClass: KClass<VM>,
        scope: ViewModelScope = ViewModelScope.ACTIVITY
    ) = (activity as? BaseActivity<*>)
        ?.getViewModel(
            viewModelClass = viewModelClass.java,
            fragmentScope = when (scope) {
                ViewModelScope.FRAGMENT -> this
                else -> null
            })
        ?: throw RuntimeException("Fragment's Activity must be of type ${BaseActivity::class.java.simpleName}")
}