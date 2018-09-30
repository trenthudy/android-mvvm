package io.hudepohl.mvvm.data.beatles

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class BeatlesModule {

    @Provides
    @Singleton
    @BeatlesAPIBaseUrl
    fun provideBeatlesAPIBaseUrl(): String = "https://hudepohl.io/"

    @Provides
    @Singleton
    fun provideBeatlesService(
            @BeatlesAPIBaseUrl baseUrl: String,
            httpClient: OkHttpClient,
            converterFactory: Converter.Factory,
            callAdapterFactory: CallAdapter.Factory
    ): BeatlesService {

        val retrofit =
                Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(httpClient)
                        .addConverterFactory(converterFactory)
                        .addCallAdapterFactory(callAdapterFactory)
                        .build()

        val beatlesApi = retrofit.create(BeatlesAPI::class.java)

        return BeatlesService(beatlesApi)
    }
}