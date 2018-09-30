package io.hudepohl.mvvm.data.beatles

import io.hudepohl.mvvm.data.beatles.model.Beatle
import io.hudepohl.mvvm.data.beatles.model.BeatleAlbum
import io.hudepohl.mvvm.data.beatles.model.BeatleName
import io.hudepohl.mvvm.util.Mockable
import io.hudepohl.mvvm.util.validateResponse
import io.reactivex.Single


@Mockable
class BeatlesService(private val api: BeatlesAPI) {

    fun getBeatleMember(name: BeatleName): Single<Beatle> =
            api.getMember(name.name.toLowerCase())
                    .flatMap { res -> validateResponse(res) }

    fun getBeatleAlbums(): Single<List<BeatleAlbum>> =
            api.getAllAlbums()
                    .flatMap { res -> validateResponse(res) }
                    .map { albumCollection -> albumCollection.albums }
}