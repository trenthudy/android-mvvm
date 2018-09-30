package io.hudepohl.mvvm.data.beatles.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BeatleAlbum(@Json(name = "name") val name: String,
                       @Json(name = "release_date") val releaseDate: String,
                       @Json(name = "length") val length: String,
                       @Json(name = "producer") val producer: String) {

    @JsonClass(generateAdapter = true)
    data class Collection(@Json(name = "albums") val albums: List<BeatleAlbum>)
}