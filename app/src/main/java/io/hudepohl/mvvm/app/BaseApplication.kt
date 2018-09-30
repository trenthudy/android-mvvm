package io.hudepohl.mvvm.app

import android.app.Application


class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
                .also {
                    appComponent = it
                    appComponent.inject(this)
                }

        setup()
    }

    private fun setup() {}
}