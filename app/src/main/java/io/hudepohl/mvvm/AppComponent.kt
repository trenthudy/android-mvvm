package io.hudepohl.mvvm

import android.app.Application
import dagger.Component
import io.hudepohl.mvvm.data.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidModule::class,
    AppModule::class,
    DataModule::class])
interface AppComponent {

    fun inject(app: Application)

    fun inject(activity: MainActivity)
}