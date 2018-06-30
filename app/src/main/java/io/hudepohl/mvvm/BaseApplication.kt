package io.hudepohl.mvvm

import android.app.Application

class BaseApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}