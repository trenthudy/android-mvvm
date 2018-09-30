package io.hudepohl.mvvm.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import io.hudepohl.mvvm.app.AppComponent
import io.hudepohl.mvvm.app.BaseApplication
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val appComponent: AppComponent
        get() = (application as BaseApplication).appComponent

    fun <VM: ViewModel> getViewModel(viewModelClass: KClass<VM>): VM =
            ViewModelProviders.of(this, viewModelFactory).get(viewModelClass.java)
}