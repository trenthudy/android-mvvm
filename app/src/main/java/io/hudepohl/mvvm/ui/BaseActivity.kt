package io.hudepohl.mvvm.ui

import android.support.v7.app.AppCompatActivity
import io.hudepohl.mvvm.app.AppComponent
import io.hudepohl.mvvm.app.BaseApplication

abstract class BaseActivity : AppCompatActivity() {

    protected val appComponent: AppComponent
        get() = (application as BaseApplication).appComponent
}