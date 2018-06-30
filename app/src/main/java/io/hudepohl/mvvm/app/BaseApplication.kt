package io.hudepohl.mvvm.app

import android.app.Application
import io.hudepohl.mvvm.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}