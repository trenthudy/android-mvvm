package io.hudepohl.mvvm.data.repo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "description") val description: String = "",
        @Json(name = "owner") val owner: RepoOwner,
        @Json(name = "private") val private: Boolean = false,
        @Json(name = "archived") val archived: Boolean = false,
        @Json(name = "open_issues_count") val openIssues: Int
)