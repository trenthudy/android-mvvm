package io.hudepohl.annotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class LogFactory(val id: Int)