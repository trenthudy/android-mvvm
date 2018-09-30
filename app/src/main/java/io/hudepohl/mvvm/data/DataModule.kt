package io.hudepohl.mvvm.data

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.hudepohl.mvvm.data.repo.RepoModule
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [
    RepoModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)
}