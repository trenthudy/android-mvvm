package io.hudepohl.mvvm.app


data class AppInfo(val appId: String,
                   val versionName: String,
                   val versionCode: Int,
                   val debug: Boolean,
                   val flavor: String,
                   val buildType: String,
                   val osVersion: String,
                   val sdkVersion: Int) {

    fun isAtLeastSdkVersion(androidSdkVersion: Int) = sdkVersion >= androidSdkVersion
}