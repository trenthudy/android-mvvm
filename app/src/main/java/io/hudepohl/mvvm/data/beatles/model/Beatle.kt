package io.hudepohl.mvvm.data.beatles.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Beatle(@Json(name = "firstName") val firstName: String,
                  @Json(name = "lastName") val lastName: String,
                  @Json(name = "nickname") val nickname: String,
                  @Json(name = "fullName") val fullName: String,
                  @Json(name = "birthDate") val birthDate: String,
                  @Json(name = "birthLocation") val birthLocation: String,
                  @Json(name = "quote") val quote: String)