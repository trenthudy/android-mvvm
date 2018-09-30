package io.hudepohl.mvvm.ui

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.hudepohl.mvvm.app.AppComponent
import io.hudepohl.mvvm.app.BaseApplication
import io.hudepohl.mvvm.ui.main.MainActivity
import javax.inject.Inject


abstract class BaseActivity<ViewModelType: BaseViewModel>(
        private val autoInitViewModel: Boolean = true
) : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: ViewModelType
    private val appComponent: AppComponent
        get() = (application as BaseApplication).appComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        performDaggerInjection()
        super.onCreate(savedInstanceState)
        if (autoInitViewModel) initializeViewModel()
    }

    private fun performDaggerInjection() = when (this) {
        is MainActivity -> appComponent.inject(this)
        else -> {}
    }

    private fun initializeViewModel() {
        val activityClassName = this::class.java.simpleName
        val viewModelClassName =
                "${activityClassName}ViewModel"
                        .replace("Activity", "", ignoreCase = false)

        val packageName = this::class.java.`package`.name
        val viewModelFullName = "$packageName.$viewModelClassName"

        @Suppress("UNCHECKED_CAST")
        val viewModelClass: Class<ViewModelType> =
                (Class.forName(viewModelFullName) as? Class<ViewModelType>)
                        ?: throw RuntimeException("Failed to create ViewModel: $viewModelFullName")

        viewModel = getViewModel(viewModelClass)

        this.lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun doOnResume() = viewModel.onResume()
        })
    }

    internal fun <VM: BaseViewModel> getViewModel(viewModelClass: Class<VM>) =
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
}