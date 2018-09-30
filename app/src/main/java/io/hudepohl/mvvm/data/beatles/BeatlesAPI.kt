package io.hudepohl.mvvm.data.beatles

import io.hudepohl.mvvm.data.beatles.model.Beatle
import io.hudepohl.mvvm.data.beatles.model.BeatleAlbum
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface BeatlesAPI {

    @GET("/api/beatles/member/{name}")
    fun getMember(
            @Path("name") name: String
    ): Single<Response<Beatle>>

    @GET("/api/beatles/album")
    fun getAllAlbums(): Single<Response<BeatleAlbum.Collection>>
}