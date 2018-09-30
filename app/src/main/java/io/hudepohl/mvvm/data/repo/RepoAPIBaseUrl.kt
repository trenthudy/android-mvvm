package io.hudepohl.mvvm.data.repo

import javax.inject.Qualifier


@Qualifier
@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class RepoAPIBaseUrl(val value: String = "")