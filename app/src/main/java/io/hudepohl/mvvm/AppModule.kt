package io.hudepohl.mvvm

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