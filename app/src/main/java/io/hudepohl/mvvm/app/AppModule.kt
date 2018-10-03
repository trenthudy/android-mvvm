package io.hudepohl.mvvm.app

import android.app.Application
import android.net.ConnectivityManager
import android.os.Build
import dagger.Module
import dagger.Provides
import io.hudepohl.mvvm.BuildConfig
import io.hudepohl.mvvm.ui.main.tabs.MainActivityTab
import io.hudepohl.mvvm.util.NetworkAvailabilityObserver
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppInfo(): AppInfo =
            AppInfo(
                    appId = BuildConfig.APPLICATION_ID,
                    versionName = BuildConfig.VERSION_NAME,
                    versionCode = BuildConfig.VERSION_CODE,
                    debug = BuildConfig.DEBUG,
                    flavor = BuildConfig.FLAVOR,
                    buildType = BuildConfig.BUILD_TYPE,
                    osVersion = Build.VERSION.RELEASE,
                    sdkVersion = Build.VERSION.SDK_INT)

    @Provides
    @Singleton
    fun provideNetworkAvailabilityObserver(
            app: Application,
            connectivityManager: ConnectivityManager
    ): NetworkAvailabilityObserver = NetworkAvailabilityObserver(app, connectivityManager)

    @Provides
    @Singleton
    fun provideDefaultMainActivityTab(): MainActivityTab = MainActivityTab.BEATLES
}