package io.hudepohl.mvvm.data.beatles

import javax.inject.Qualifier


@Qualifier
@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BeatlesAPIBaseUrl(val value: String = "")