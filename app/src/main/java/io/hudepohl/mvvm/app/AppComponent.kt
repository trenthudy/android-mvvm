package io.hudepohl.mvvm.app

import android.app.Application
import dagger.Component
import io.hudepohl.mvvm.ui.main.MainActivity
import io.hudepohl.mvvm.data.DataModule
import io.hudepohl.mvvm.ui.ViewModelFactoryModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidModule::class,
    AppModule::class,
    ViewModelFactoryModule::class,
    DataModule::class])
interface AppComponent {

    fun inject(app: Application)

    fun inject(activity: MainActivity)
}