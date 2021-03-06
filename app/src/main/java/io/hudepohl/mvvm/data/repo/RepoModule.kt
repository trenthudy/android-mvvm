package io.hudepohl.mvvm.data.repo

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class RepoModule {

    @Provides
    @Singleton
    @RepoAPIBaseUrl
    fun provideRepoAPIBaseUrl(): String = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideRepoService(
            @RepoAPIBaseUrl baseUrl: String,
            httpClient: OkHttpClient,
            converterFactory: Converter.Factory,
            callAdapterFactory: CallAdapter.Factory
    ): RepoService {

        val retrofit =
                Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(httpClient)
                        .addConverterFactory(converterFactory)
                        .addCallAdapterFactory(callAdapterFactory)
                        .build()

        val repoApi = retrofit.create(RepoAPI::class.java)

        return RepoService(repoApi)
    }
}