package io.hudepohl.mvvm.data

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.hudepohl.mvvm.data.repo.RepoAPI
import io.hudepohl.mvvm.data.repo.RepoAPIBaseUrl
import io.hudepohl.mvvm.data.repo.RepoService
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideJsonConverter(): Converter.Factory =
            MoshiConverterFactory
                    .create(Moshi.Builder().build())

    @Provides
    @Singleton
    fun provideRetrofit(
            @RepoAPIBaseUrl baseUrl: String,
            client: OkHttpClient,
            converter: Converter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Provides
    @Singleton
    fun provideRepoAPI(retrofit: Retrofit): RepoAPI = retrofit.create(RepoAPI::class.java)

    @Provides
    @Singleton
    fun provideRepoService(api: RepoAPI): RepoService = RepoService(api)
}