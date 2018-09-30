package io.hudepohl.mvvm.data.repo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RepoOwner(
        @Json(name = "login") val username: String
)