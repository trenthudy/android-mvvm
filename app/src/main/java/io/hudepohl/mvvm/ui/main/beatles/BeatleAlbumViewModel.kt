package io.hudepohl.mvvm.ui.main.beatles

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.data.beatles.BeatlesService
import io.hudepohl.mvvm.data.beatles.model.BeatleAlbum
import io.hudepohl.mvvm.ui.BaseViewModel
import io.hudepohl.mvvm.util.NetworkAvailability
import io.hudepohl.mvvm.util.NetworkAvailabilityObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BeatleAlbumViewModel(
    app: Application,
    private val beatlesService: BeatlesService,
    private val network: NetworkAvailabilityObserver
) : BaseViewModel(app) {

    val albums = MutableLiveData<List<BeatleAlbum>>()

    override fun setup() {

        network
            .getNetworkAvailability()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .listenOnViewModel { networkUpdate ->
                when (networkUpdate) {
                    NetworkAvailability.AVAILABLE -> {
                        if (albums.value == null) retrieveBeatleAlbums()
                    }
                    NetworkAvailability.NOT_AVAILABLE -> {
                        errorMessages.value = getApp().getString(R.string.error_offline)
                    }
                    null -> {

                    }
                }
            }
    }

    private fun retrieveBeatleAlbums() {

        beatlesService
            .getBeatleAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .listenOnViewModel(
                { albumList ->
                    albums.value = albumList
                },
                { _ ->
                    errorMessages.value = getApp().getString(R.string.error_generic)
                })
    }
}