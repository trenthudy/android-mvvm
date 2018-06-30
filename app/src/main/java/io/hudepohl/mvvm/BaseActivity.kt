package io.hudepohl.mvvm

import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected val appComponent: AppComponent
        get() = (application as BaseApplication).appComponent
}