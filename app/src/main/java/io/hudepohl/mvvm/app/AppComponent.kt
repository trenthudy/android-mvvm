package io.hudepohl.mvvm.app

import android.app.Application
import dagger.Component
import io.hudepohl.mvvm.ui.main.MainActivity
import io.hudepohl.mvvm.data.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DataModule::class])
interface AppComponent {

    fun inject(app: Application)

    fun inject(activity: MainActivity)
}