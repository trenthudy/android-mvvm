package io.hudepohl.mvvm.app

import dagger.Module
import dagger.Provides
import io.hudepohl.mvvm.data.repo.RepoAPIBaseUrl
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @RepoAPIBaseUrl
    fun provideRepoAPIBaseUrl(): String = "https://api.github.com/"
}