package io.hudepohl.mvvm.util

import io.reactivex.Single
import retrofit2.Response


fun <T> validateResponse(response: Response<T>): Single<T> =
        if (response.isSuccessful && response.body() != null) {
            Single.just(response.body())
        } else {
            Single.error(Throwable("response validation failed"))
        }